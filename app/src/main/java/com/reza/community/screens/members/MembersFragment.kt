package com.reza.community.screens.members

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import by.kirich1409.viewbindingdelegate.viewBinding
import com.reza.community.R
import com.reza.community.databinding.FragmentMembersBinding
import com.reza.presentation.MembersViewModel
import kotlinx.coroutines.launch
import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback
import org.imaginativeworld.oopsnointernet.dialogs.signal.NoInternetDialogSignal
import timber.log.Timber
import javax.inject.Inject


class MembersFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : Fragment(R.layout.fragment_members) {

    private val viewModel by viewModels<MembersViewModel> { viewModelFactory }
    private val binding: FragmentMembersBinding by viewBinding()

    private val membersAdapter by lazy { MembersAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        initObservers()

        checkInternetAndGetMembers()
    }

    private fun initRecycler(){
        membersAdapter.onItemLiked = {
            viewModel.memberLiked(it)
        }
        membersAdapter.onItemUnliked = {
            viewModel.memberUnliked(it)
        }

        binding.recyclerMembers.adapter = membersAdapter
    }

    private fun initObservers() {
        viewModel.membersStreamLiveData.observe(viewLifecycleOwner, { members ->
            members.map {
                Timber.e("Member ${it.firstName}")
            }
            lifecycleScope.launch {
                membersAdapter.submitData(members)
            }

            setErrorViewsVisibility(false)
            setMembersListVisibility(true)
        })

        viewModel.errorEvent.observe(viewLifecycleOwner) {
            setErrorViewsVisibility(true)
            setMembersListVisibility(false)
            Toast.makeText(requireContext(),"Error: $it",Toast.LENGTH_LONG).show()
        }

        viewModel.loadingEvent.observe(viewLifecycleOwner){
            setLoadingAnimation(it)
        }
    }


    private fun checkInternetAndGetMembers(){
        // Check internet and if it's not connected, will show dialog to the user
        NoInternetDialogSignal.Builder(
            requireActivity(),
            lifecycle
        ).apply {
            dialogProperties.apply {
                connectionCallback = object : ConnectionCallback {
                    override fun hasActiveConnection(hasActiveConnection: Boolean) {
                        if (hasActiveConnection)
                            viewModel.getMembers()
                    }
                }

                cancelable = false
                noInternetConnectionTitle = getString(R.string.not_internet)
                noInternetConnectionMessage = getString(R.string.check_your_internet)

                showInternetOnButtons = true
                pleaseTurnOnText = getString(R.string.please_turn_on)
                wifiOnButtonText = getString(R.string.wifi)
                mobileDataOnButtonText = getString(R.string.mobile_data)

                onAirplaneModeTitle = getString(R.string.not_internet)
                onAirplaneModeMessage = getString(R.string.on_airplane_message)
                pleaseTurnOffText = getString(R.string.please_turn_off)
                airplaneModeOffButtonText = getString(R.string.airplane_mode_off)
                showAirplaneModeOffButtons = true
            }
        }.build()
    }

    private fun setLoadingAnimation (loading: Boolean) {
        binding.lottieViewLoading.apply {
            isVisible = loading
            if (loading)
                playAnimation()
            else{
                cancelAnimation()
            }

        }
    }


    private fun setErrorViewsVisibility(show: Boolean) {
        binding.txtErrorDescription.isVisible = show
        binding.lottieViewError.isVisible = show
    }

    private fun setMembersListVisibility(show: Boolean) {
        binding.recyclerMembers.isVisible = show
    }

}