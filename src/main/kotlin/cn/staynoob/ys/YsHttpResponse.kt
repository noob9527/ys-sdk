package cn.staynoob.ys

data class YsHttpResponse<out T>(
        val code: Int,
        val msg: String,
        val data: T?
)

