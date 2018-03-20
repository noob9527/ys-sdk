package cn.staynoob.ys.http

import cn.staynoob.ys.YsHttpResponse
import cn.staynoob.ys.domain.response.PassengerFlowConfig
import cn.staynoob.ys.domain.response.PassengerFlowDaily
import cn.staynoob.ys.domain.response.PassengerFlowHourly
import cn.staynoob.ys.domain.response.PassengerFlowSwitchStatus
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.time.Instant
import java.time.temporal.ChronoUnit

interface YsPassengerFlowClient {

    @POST("passengerflow/switch/status")
    @FormUrlEncoded
    fun getSwitchStatus(
            @Field("deviceSerial") deviceSerial: String
    ): Call<YsHttpResponse<PassengerFlowSwitchStatus>>

    @POST("passengerflow/switch/set")
    @FormUrlEncoded
    fun setSwitch(
            @Field("deviceSerial") deviceSerial: String,
            @Field("enable") enable: Int,
            @Field("channelNo") channelNo: Int? = null
    ): Call<YsHttpResponse<Unit>>

    @POST("passengerflow/daily")
    @FormUrlEncoded
    fun getDaily(
            @Field("deviceSerial") deviceSerial: String,
            @Field("channelNo") channelNo: Int = 1,
            @Field("date") date: Long = Instant.now().minus(1, ChronoUnit.DAYS).toEpochMilli()
    ): Call<YsHttpResponse<PassengerFlowDaily>>

    @POST("passengerflow/hourly")
    @FormUrlEncoded
    fun getHourly(
            @Field("deviceSerial") deviceSerial: String,
            @Field("channelNo") channelNo: Int = 1,
            @Field("date") date: Long = Instant.now().toEpochMilli()
    ): Call<YsHttpResponse<List<PassengerFlowHourly>>>

    @POST("passengerflow/config/set")
    @FormUrlEncoded
    fun setConfig(
            @Field("deviceSerial") deviceSerial: String,
            @Field("line") line: String,
            @Field("direction") direction: String,
            @Field("channelNo") channelNo: Int? = null
    ): Call<YsHttpResponse<Unit>>

    @POST("passengerflow/config/get")
    @FormUrlEncoded
    fun getConfig(
            @Field("deviceSerial") deviceSerial: String,
            @Field("channelNo") channelNo: Int? = null
    ): Call<YsHttpResponse<PassengerFlowConfig>>
}