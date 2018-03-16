package cn.staynoob.ys.integration

import cn.staynoob.ys.YsProperties
import cn.staynoob.ys.YsService
import cn.staynoob.ys.autoconfigure.YsAutoConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration

@ImportAutoConfiguration(YsAutoConfiguration::class)
class YsApiServiceIt : IntegrationTestBase() {

    @Autowired
    private lateinit var service: YsService
    @Autowired
    private lateinit var properties: YsProperties

    @Test
    @DisplayName("test100")
    fun test100() {
        val res = service.listVideoAddress()
        assertThat(res).isNotNull()
    }

}