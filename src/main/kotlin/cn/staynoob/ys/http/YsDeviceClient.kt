package cn.staynoob.ys.http

import cn.staynoob.ys.YsHttpResponse
import cn.staynoob.ys.domain.response.Picture
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface YsDeviceClient {

    @POST("device/add")
    @FormUrlEncoded
    fun addDevice(
            @Field("deviceSerial") deviceSerial: String,
            @Field("validateCode") validateCode: String
    ): Call<YsHttpResponse<Unit>>

    @POST("device/encrypt/off")
    @FormUrlEncoded
    fun turnOffEncrypt(
            @Field("deviceSerial") deviceSerial: String,
            @Field("validateCode") validateCode: String
    ): Call<YsHttpResponse<Unit>>

    @POST("device/capture")
    @FormUrlEncoded
    fun capture(
            @Field("deviceSerial") deviceSerial: String,
            @Field("channelNo") channelNo: Int = 1
    ): Call<YsHttpResponse<Picture>>
}