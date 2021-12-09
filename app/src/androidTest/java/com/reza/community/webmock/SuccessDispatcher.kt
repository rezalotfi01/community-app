package com.reza.community.webmock

import android.content.Context
import android.net.Uri
import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import com.reza.community.webmock.AssetReaderUtil.asset


const val MEMBERS_SUCCESS = "members_success.json"

class SuccessDispatcher(
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
) : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        val errorResponse = MockResponse().setResponseCode(404)

        if (Uri.parse(request.path).path == null)
            return errorResponse

        val responseFile = MEMBERS_SUCCESS

        return run {
            val responseBody = asset(context, responseFile)
            MockResponse().setResponseCode(200).setBody(responseBody)
        }
    }
}