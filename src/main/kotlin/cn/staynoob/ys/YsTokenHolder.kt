package cn.staynoob.ys

import cn.staynoob.ys.domain.response.YsToken
import cn.staynoob.ys.http.YsTokenClient

internal class YsTokenHolder(
        private val property: YsProperties,
        private val tokenClient: YsTokenClient,
        private var token: YsToken? = null
) {


    /**
     * get a valid token from cache or ys service
     *
     * if token is null or token is expired, fetch and return cached token
     * else if token is about to expire, refresh and return cached token
     * else return cached token
     */
    @Synchronized
    fun getAccessToken(): String {
        val token = this.token
        if (token == null || token.isExpired || token.isAboutToExpire) {
            val newToken = tokenClient
                    .getAccessToken(
                            property.appKey,
                            property.appSecret
                    ).getNonNullData()
            this.token = newToken
            return newToken.accessToken
        } else {
            return token.accessToken
        }
    }

}