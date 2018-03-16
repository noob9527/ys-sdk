package cn.staynoob.ys.http

import cn.staynoob.ys.YsTokenHolder
import cn.staynoob.ys.appendParam
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response

internal class YsTokenInterceptor(
        private val ysTokenHolder: YsTokenHolder
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        var newFormBody = original.body()
        if (original.method() == "POST") {
            val originalBody = original.body()
            if (originalBody is FormBody?) {
                newFormBody = appendParam(
                        originalBody,
                        mapOf(
                                "accessToken" to ysTokenHolder.getAccessToken()
                        )
                )
            }
        }
        val request = original.newBuilder()
                .method(original.method(), newFormBody)
                .build()
        return chain.proceed(request)
    }
}
