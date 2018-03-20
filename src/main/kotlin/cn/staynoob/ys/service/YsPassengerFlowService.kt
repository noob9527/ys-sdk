package cn.staynoob.ys.service

import cn.staynoob.ys.domain.response.PassengerFlowConfig
import cn.staynoob.ys.domain.response.PassengerFlowDaily
import cn.staynoob.ys.domain.response.PassengerFlowHourly
import cn.staynoob.ys.domain.response.PassengerFlowSwitchStatus
import java.time.Instant
import java.time.temporal.ChronoUnit

interface YsPassengerFlowService {

    fun getSwitchStatus(
            deviceSerial: String
    ): PassengerFlowSwitchStatus

    fun setSwitch(
            status: PassengerFlowSwitchStatus
    )

    fun getDaily(
            deviceSerial: String,
            channelNo: Int = 1,
            date: Instant = Instant.now().minus(1, ChronoUnit.DAYS)
    ): PassengerFlowDaily?

    fun getHourly(
            deviceSerial: String,
            channelNo: Int = 1,
            date: Instant = Instant.now()
    ): List<PassengerFlowHourly>

    fun getConfig(
            deviceSerial: String,
            channelNo: Int? = null
    ): PassengerFlowConfig

    fun setConfig(
            deviceSerial: String,
            config: PassengerFlowConfig,
            channelNo: Int? = null
    )
}