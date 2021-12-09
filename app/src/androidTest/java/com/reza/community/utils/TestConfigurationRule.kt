package com.reza.community.utils

import androidx.test.core.app.memberActivity
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import com.reza.community.MainActivity
import com.reza.community.webmock.injectTestConfiguration

class TestConfigurationRule : TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)

        injectTestConfiguration {}
        memberActivity<MainActivity>()
    }
}