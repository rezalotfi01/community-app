package com.reza.community.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.reza.core.di.FragmentKey
import com.reza.core.di.ViewModelKey
import com.reza.presentation.MembersViewModel
import com.reza.community.screens.members.MembersFragment
import com.reza.community.MainActivity

@Module
internal abstract class MainActivityModule {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @Binds
    @IntoMap
    @FragmentKey(MembersFragment::class)
    abstract fun membersFragment(membersFragment: MembersFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(MembersViewModel::class)
    @ExperimentalCoroutinesApi
    abstract fun membersViewModel(membersViewModel: MembersViewModel): ViewModel
}
