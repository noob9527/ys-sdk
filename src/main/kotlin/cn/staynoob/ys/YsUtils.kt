package cn.staynoob.ys

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import okhttp3.FormBody
import retrofit2.Call
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

internal val objectMapper = ObjectMapper()
        .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
        .disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS)
        .registerModule(JavaTimeModule())
        .registerKotlinModule()

internal val dateFormatter = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss")
        .withLocale(Locale.getDefault())
        .withZone(ZoneId.systemDefault())

internal operator fun FormBody.iterator(): Iterator<Pair<String, String>> {
    return object : Iterator<Pair<String, String>> {
        private var currentIndex = 0
        override fun hasNext(): Boolean {
            return currentIndex < this@iterator.size()
        }

        override fun next(): Pair<String, String> {
            val res = this@iterator.name(currentIndex) to this@iterator.value(currentIndex)
            currentIndex += 1
            return res
        }
    }
}

internal fun <T> Call<out YsHttpResponse<T>>.exec(): YsHttpResponse<T> {
    val res = this.execute()
    if (!res.isSuccessful) {
        val errMsg = res.errorBody()?.string()
        var message = res.message()
        if (errMsg != null) {
            message = try {
                objectMapper
                        .readValue<YsHttpResponse<Any>>(errMsg)
                        .msg
            } catch (e: Exception) {
                errMsg
            }
        }
        throw YsApiException(message)
    }
    val body = res.body()!!
    if (body.code != 200) {
        throw YsApiException(body.msg, body.code)
    }
    return body
}

internal fun <T> Call<out YsHttpResponse<T>>.getData(): T? = this.exec().data

internal fun <T> Call<out YsHttpResponse<T>>.getNonNullData(): T = this.getData()!!

internal fun appendParam(
        formBody: FormBody?,
        param: Map<String, String>
): FormBody {
    val original = formBody?.iterator()?.asSequence()?.toMap()
            ?: mapOf()
    val builder = FormBody.Builder()
    (param + original).entries
            .forEach { builder.add(it.key, it.value) }
    return builder.build()
}

