package cn.staynoob.ys

import cn.staynoob.ys.domain.response.YsToken
import cn.staynoob.ys.http.YsTokenClient
import org.apache.log4j.Logger

internal class YsTokenHolder(
        private val property: YsProperties,
        private val tokenClient: YsTokenClient,
        private var token: YsToken? = null
) {

    companion object {
        private val log = Logger.getLogger(YsTokenHolder::class.java)
    }

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

        if (log.isDebugEnabled) {
            log.debug("current token info:")
            log.debug("token:" + this.token)
            log.debug("expireTime:" + this.token?.let { dateFormatter.format(it.expireTime) })
            log.debug("isExpired:" + this.token?.isExpired)
            log.debug("isAboutToExpire:" + this.token?.isAboutToExpire)
        }
        if (token == null || token.isExpired || token.isAboutToExpire) {
            val newToken = tokenClient
                    .getAccessToken(
                            property.appKey,
                            property.appSecret
                    ).getNonNullData()
            this.token = newToken
            if (log.isDebugEnabled) {
                log.debug("new token info:")
                log.debug("token:" + this.token)
                log.debug("expireTime:" + this.token?.let {
                    dateFormatter.format(it.expireTime)
                })
                log.debug("isExpired:" + this.token?.isExpired)
                log.debug("isAboutToExpire:" + this.token?.isAboutToExpire)
            }
            return newToken.accessToken
        } else {
            return token.accessToken
        }
    }

}