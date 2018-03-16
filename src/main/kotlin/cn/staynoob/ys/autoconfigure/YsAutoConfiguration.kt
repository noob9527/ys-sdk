package cn.staynoob.ys.autoconfigure

import cn.staynoob.ys.*
import cn.staynoob.ys.http.YsApiService
import cn.staynoob.ys.http.YsTokenInterceptor
import cn.staynoob.ys.http.YsTokenService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.boot.autoconfigure.AutoConfigureOrder
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@Configuration
@AutoConfigureOrder
@EnableConfigurationProperties(YsProperties::class)
class YsAutoConfiguration(
        private val properties: YsProperties
) {

    private val loggingInterceptor = HttpLoggingInterceptor()
            .apply {
                level = properties.logLevel
            }

    companion object {
        private val jacksonConverterFactory =
                JacksonConverterFactory.create(objectMapper)
    }

    @Bean
    @ConditionalOnMissingBean
    internal fun ysTokenService(): YsTokenService {
        val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(properties.apiUrl)
                .addConverterFactory(jacksonConverterFactory)
                .client(client)
                .build()
        return retrofit.create(YsTokenService::class.java)
    }

    @Bean
    @ConditionalOnMissingBean
    internal fun ysTokenHolder(
            tokenService: YsTokenService
    ): YsTokenHolder {
        return YsTokenHolder(properties, tokenService)
    }

    @Bean
    @ConditionalOnMissingBean
    internal fun ysApiService(
            ysTokenHolder: YsTokenHolder
    ): YsApiService {
        val client = OkHttpClient.Builder()
                .addInterceptor(YsTokenInterceptor(ysTokenHolder))
                .addInterceptor(loggingInterceptor)
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(properties.apiUrl)
                .addConverterFactory(jacksonConverterFactory)
                .client(client)
                .build()
        return retrofit.create(YsApiService::class.java)
    }

    @Bean
    @ConditionalOnMissingBean
    internal fun ysService(
            ysApiService: YsApiService
    ): YsService {
        return YsServiceImpl(ysApiService)
    }
}