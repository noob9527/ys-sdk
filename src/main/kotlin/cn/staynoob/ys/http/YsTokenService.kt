package cn.staynoob.ys.http

import cn.staynoob.ys.YsHttpResponse
import cn.staynoob.ys.domain.response.YsToken
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

internal interface YsTokenService {
    @POST("token/get")
    @FormUrlEncoded
    fun getAccessToken(
            @Field("appKey") appKey: String,
            @Field("appSecret") appSecret: String
    ): Call<YsHttpResponse<YsToken>>
}