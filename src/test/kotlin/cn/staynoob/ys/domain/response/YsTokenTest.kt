package cn.staynoob.ys.domain.response

import cn.staynoob.ys.objectMapper
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.Instant

class YsTokenTest {
    private lateinit var mapper: ObjectMapper

    private val expireTime = 1527496245417
    private val json = """{"accessToken":"accessToken","expireTime":$expireTime}"""

    @BeforeEach
    fun setUp() {
        mapper = objectMapper
    }

    @Test
    @DisplayName("serialize test")
    fun test100() {
        val token = YsToken(
                "accessToken",
                Instant.ofEpochMilli(expireTime)
        )
        val res = mapper.writeValueAsString(token)
        assertThat(res).isEqualTo(json)
    }

    @Test
    @DisplayName("deserialize test")
    fun test200() {
        val res = mapper.readValue<YsToken>(json)
        val expected = YsToken(
                "accessToken",
                Instant.ofEpochMilli(expireTime)
        )
        assertThat(res).isEqualTo(expected)
    }

    @Test
    @DisplayName("isExpired")
    fun test300() {
        val token1 = YsToken(
                "accessToken",
                Instant.now().minusSeconds(1)
        )
        assertThat(token1.isExpired).isTrue()
        val token2 = token1.copy(
                expireTime = Instant.now().plusSeconds(1)
        )
        assertThat(token2.isExpired).isFalse()
    }

    @Test
    @DisplayName("isAboutToExpire")
    fun test400() {
        val token1 = YsToken(
                "accessToken",
                Instant.now().plusSeconds(10)
        )
        assertThat(token1.isAboutToExpire).isTrue()
        val token2 = token1.copy(
                expireTime = Instant.now().plusSeconds(30)
        )
        assertThat(token2.isAboutToExpire).isFalse()
    }
}