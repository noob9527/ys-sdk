package cn.staynoob.ys.http

import cn.staynoob.ys.YsHttpResponse
import cn.staynoob.ys.domain.response.VideoAddress
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

internal interface YsApiService {

    @POST("live/video/list")
    @FormUrlEncoded
    fun listVideoAddress(
            @Field("pageStart") pageStart: Int,
            @Field("pageSize") pageSize: Int
    ): Call<YsHttpResponse<List<VideoAddress>>>

    @POST("live/address/limited")
    @FormUrlEncoded
    fun fetchLimitedAddress(
            @Field("deviceSerial") deviceSerial: String,
            @Field("channelNo") channelNo: Int = 1,
            @Field("expireTime") expireTime: Int? = null
    ): Call<YsHttpResponse<VideoAddress>>
}

