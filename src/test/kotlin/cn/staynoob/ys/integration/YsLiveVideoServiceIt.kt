package cn.staynoob.ys.integration

import cn.staynoob.ys.autoconfigure.YsAutoConfiguration
import cn.staynoob.ys.service.YsLiveVideoService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.ImportAutoConfiguration

@ImportAutoConfiguration(YsAutoConfiguration::class)
class YsLiveVideoServiceIt : IntegrationTestBase() {

    @Value("\${ys-test-serial-no}")
    private lateinit var testSerialNo: String

    @Autowired
    private lateinit var service: YsLiveVideoService

    @Test
    @DisplayName("listAddressTest")
    fun listAddressTest() {
        val res = service.listAddress()
        assertThat(res).isNotNull()
    }

    @Test
    @DisplayName("fetchLimitedAddressTest")
    fun fetchLimitedAddressTest() {
        val res = service.fetchLimitedAddress(testSerialNo)
        assertThat(res).isNotNull()
    }
}