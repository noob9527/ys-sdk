package cn.staynoob.ys

import cn.staynoob.ys.util.NoArgConstructor
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.boot.context.properties.ConfigurationProperties
import javax.annotation.PostConstruct

/**
 * this class may be construct by two different ways
 * let's say the kotlin way(by primary constructor)
 * and the java way(by Class.instantiate())
 * this class has to be mutable, otherwise spring won't be
 * able to set up its property since spring create this class via the "java" way.
 */
@NoArgConstructor
@ConfigurationProperties(prefix = "ys")
class YsProperties(
        var appKey: String,
        var appSecret: String,
        var apiUrl: String = "https://open.ys7.com/api/lapp/",
        var logLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.NONE
) {
    /**
     * fix kotlin init logic dose not execute issue
     */
    @Suppress("SENSELESS_COMPARISON")
    @PostConstruct
    private fun init() {
        if (apiUrl == null) apiUrl = "https://open.ys7.com/api/lapp/"
        if (logLevel == null) logLevel = HttpLoggingInterceptor.Level.NONE
    }
}

