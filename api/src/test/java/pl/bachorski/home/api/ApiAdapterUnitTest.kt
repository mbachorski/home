package pl.bachorski.home.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import pl.bachorski.home.api.fakes.FakeApiService


class ApiAdapterUnitTest {
    @Test
    fun `getDevices() return DeviceBinarySwitch list`() = runBlockingTest {
        // given
        val apiAdapter = ApiAdapter(FakeApiService())
        // when
        val devices = apiAdapter.getDevices()
        // then
        println("TESTS RESULTS: items count: ${devices.size}")
        assertThat(devices.size).isEqualTo(1)
    }
}