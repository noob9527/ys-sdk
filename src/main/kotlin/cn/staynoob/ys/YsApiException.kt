package cn.staynoob.ys

class YsApiException(
        message: String,
        val code: Int? = null
) : RuntimeException(message)
