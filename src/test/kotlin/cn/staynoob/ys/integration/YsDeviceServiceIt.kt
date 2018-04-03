package cn.staynoob.ys.integration

import cn.staynoob.ys.autoconfigure.YsAutoConfiguration
import cn.staynoob.ys.service.YsDeviceService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.ImportAutoConfiguration

@ImportAutoConfiguration(YsAutoConfiguration::class)
class YsDeviceServiceIt : IntegrationTestBase() {

    @Value("\${ys-test-serial-no}")
    private lateinit var testSerialNo: String

    @Autowired
    private lateinit var service: YsDeviceService

    @Test
    @DisplayName("captureTest")
    fun captureTest() {
        val res = service.capture(testSerialNo)
        assertThat(res).isNotNull()
    }
}