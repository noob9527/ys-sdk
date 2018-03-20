package cn.staynoob.ys.integration

import cn.staynoob.ys.autoconfigure.YsAutoConfiguration
import cn.staynoob.ys.domain.Segment
import cn.staynoob.ys.domain.response.PassengerFlowConfig
import cn.staynoob.ys.domain.response.PassengerFlowSwitchStatus
import cn.staynoob.ys.service.YsPassengerFlowService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.ImportAutoConfiguration

@ImportAutoConfiguration(YsAutoConfiguration::class)
class YsPassengerFlowServiceIt : IntegrationTestBase() {

    @Value("\${ys-test-serial-no}")
    private lateinit var testSerialNo: String

    @Autowired
    private lateinit var service: YsPassengerFlowService

    @Test
    @DisplayName("getSwitchStatusTest")
    fun getSwitchStatusTest() {
        val res = service.getSwitchStatus(testSerialNo)
        assertThat(res).isNotNull()
    }

    @Test
    @DisplayName("setSwitchTest")
    fun setSwitchTest() {
        val status = PassengerFlowSwitchStatus(
                testSerialNo, 1, 1
        )
        service.setSwitch(status)
    }

    @Test
    @DisplayName("getDailyTest")
    fun getDailyTest() {
        val res = service.getDaily(testSerialNo)
        assertThat(res).isNotNull()
    }

    @Test
    @DisplayName("getHourlyTest")
    fun getHourlyTest() {
        val res = service.getHourly(testSerialNo)
        assertThat(res).isNotNull()
    }

    @Test
    @DisplayName("getConfigTest")
    fun getConfigTest() {
        val res = service.getConfig(testSerialNo)
        assertThat(res).isNotNull()
    }

    @Test
    @DisplayName("setConfigTest")
    fun setConfigTest() {
        val config = PassengerFlowConfig(
                Segment(0.5, 0.0, 0.5, 1.0),
                Segment(0.5, 0.5, 0.26, 0.5)
        )
        service.setConfig(
                testSerialNo,
                config
        )
    }
}