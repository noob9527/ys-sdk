package cn.staynoob.ys.integration

import cn.staynoob.ys.YsProperties
import cn.staynoob.ys.autoconfigure.YsAutoConfiguration
import cn.staynoob.ys.http.YsTokenService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration

@ImportAutoConfiguration(YsAutoConfiguration::class)
@Disabled("token api 有调用次数限制")
class YsTokenServiceIt : IntegrationTestBase() {

    @Autowired
    private lateinit var service: YsTokenService
    @Autowired
    private lateinit var properties: YsProperties

    @Test
    @DisplayName("test100")
    fun test100() {
        val call = service.getAccessToken(
                properties.appKey,
                properties.appSecret
        )
        val res = call.execute()
        assertThat(res.body()).isNotNull()
    }
}