package cn.staynoob.ys

import cn.staynoob.ys.domain.response.YsToken
import cn.staynoob.ys.http.YsTokenService

internal class YsTokenHolder(
        private val property: YsProperties,
        private val tokenService: YsTokenService
) {

    private var token: YsToken? = null

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
            val newToken = tokenService
                    .getAccessToken(
                            property.appKey,
                            property.appSecret
                    ).toResult()
            this.token = newToken
            return newToken.accessToken
        } else {
            return token.accessToken
        }
    }

}