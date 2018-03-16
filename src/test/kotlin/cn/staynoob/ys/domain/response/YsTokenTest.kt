package cn.staynoob.ys.domain.response

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.Instant

class YsTokenTest {
    private lateinit var mapper: ObjectMapper

    @BeforeEach
    fun setUp() {
        mapper = ObjectMapper()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                .registerModule(JavaTimeModule())
                .registerKotlinModule()
    }

    @Test
    @DisplayName("serialize test")
    fun test100() {
        val token = YsToken(
                "account",
                Instant.now()
        )
        mapper.writeValueAsString(token)
    }

    @Test
    @DisplayName("deserialize test")
    fun test200() {
        val json = """
            {
                "accessToken": "7da3330ec28e3996b6ef4a7123456789",
                "expireTime": 1470810222045
            }
        """
        mapper.readValue<YsToken>(json)
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