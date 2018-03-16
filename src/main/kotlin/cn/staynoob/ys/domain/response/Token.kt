package cn.staynoob.ys.domain.response

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.Instant

/**
 * indicates if token is about to expire(unit second)
 */
const val BUFFER_DURATION: Int = 20

data class YsToken(
        val accessToken: String,
        val expireTime: Instant
) {
    @get:JsonIgnore
    val isExpired: Boolean
        get() = Instant.now() > expireTime

    @get:JsonIgnore
    val isAboutToExpire: Boolean
        get() = Instant.now() > expireTime.minusSeconds((BUFFER_DURATION).toLong())
}