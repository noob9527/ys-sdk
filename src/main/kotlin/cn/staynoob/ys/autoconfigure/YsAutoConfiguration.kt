package cn.staynoob.ys.autoconfigure

import cn.staynoob.ys.YsProperties
import cn.staynoob.ys.YsTokenHolder
import cn.staynoob.ys.domain.response.YsToken
import cn.staynoob.ys.http.*
import cn.staynoob.ys.objectMapper
import cn.staynoob.ys.service.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.boot.autoconfigure.AutoConfigureOrder
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.time.Instant

@Configuration
@AutoConfigureOrder
@EnableConfigurationProperties(YsProperties::class)
class YsAutoConfiguration(
        private val properties: YsProperties,
        private val beanFactory: ConfigurableBeanFactory
) {

    @Value("\${ys-test-token:#{null}}")
    private val accessToken: String? = null

    private val loggingInterceptor = HttpLoggingInterceptor()
            .apply {
                level = properties.logLevel
            }

    companion object {
        private val jacksonConverterFactory =
                JacksonConverterFactory.create(objectMapper)
    }

    internal fun ysTokenClient(): YsTokenClient {
        val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(properties.apiUrl)
                .addConverterFactory(jacksonConverterFactory)
                .client(client)
                .build()
        return retrofit.create(YsTokenClient::class.java)
    }

    @Bean
    @ConditionalOnMissingBean
    internal fun ysTokenHolder(): YsTokenHolder {
        // just for test
        val token = if (accessToken != null)
            YsToken(accessToken!!, Instant.now().plusSeconds(3600))
        else null
        return YsTokenHolder(properties, ysTokenClient(), token)
    }

    @Bean
    @ConditionalOnMissingBean
    internal fun ysRetrofit(
            tokenHolder: YsTokenHolder
    ): Retrofit {
        val client = OkHttpClient.Builder()
                .addInterceptor(YsTokenInterceptor(tokenHolder))
                .addInterceptor(loggingInterceptor)
                .build()
        return Retrofit.Builder()
                .baseUrl(properties.apiUrl)
                .addConverterFactory(jacksonConverterFactory)
                .client(client)
                .build()
    }

    @Bean
    @ConditionalOnMissingBean
    internal fun ysLiveVideoService(ysRetrofit: Retrofit): YsLiveVideoService {
        return YsLiveVideoServiceImpl(ysRetrofit.create(YsLiveVideoClient::class.java))
    }

    @Bean
    @ConditionalOnMissingBean
    internal fun ysPassengerFlowService(ysRetrofit: Retrofit): YsPassengerFlowService {
        return YsPassengerFlowServiceImpl(ysRetrofit.create(YsPassengerFlowClient::class.java))
    }

    @Bean
    @ConditionalOnMissingBean
    internal fun ysDeviceService(ysRetrofit: Retrofit): YsDeviceService {
        return YsDeviceServiceImpl(ysRetrofit.create(YsDeviceClient::class.java))
    }
}