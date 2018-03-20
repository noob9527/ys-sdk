package cn.staynoob.ys.service

import cn.staynoob.ys.domain.response.PassengerFlowConfig
import cn.staynoob.ys.domain.response.PassengerFlowDaily
import cn.staynoob.ys.domain.response.PassengerFlowHourly
import cn.staynoob.ys.domain.response.PassengerFlowSwitchStatus
import cn.staynoob.ys.exec
import cn.staynoob.ys.getData
import cn.staynoob.ys.http.YsPassengerFlowClient
import cn.staynoob.ys.objectMapper
import cn.staynoob.ys.getNonNullData
import java.time.Instant

internal class YsPassengerFlowServiceImpl(
        private val passengerFlowClient: YsPassengerFlowClient
) : YsPassengerFlowService {
    override fun setSwitch(status: PassengerFlowSwitchStatus) {
        passengerFlowClient.setSwitch(
                status.deviceSerial,
                status.enable,
                status.channelNo
        ).exec()
    }

    override fun getDaily(deviceSerial: String, channelNo: Int, date: Instant): PassengerFlowDaily? {
        return passengerFlowClient.getDaily(
                deviceSerial, channelNo, date.toEpochMilli()
        ).getData()
    }

    override fun getHourly(deviceSerial: String, channelNo: Int, date: Instant): List<PassengerFlowHourly> {
        return passengerFlowClient.getHourly(
                deviceSerial, channelNo, date.toEpochMilli()
        ).getNonNullData()
    }

    override fun getSwitchStatus(deviceSerial: String): PassengerFlowSwitchStatus {
        return passengerFlowClient.getSwitchStatus(deviceSerial).getNonNullData()
    }

    override fun setConfig(deviceSerial: String, config: PassengerFlowConfig, channelNo: Int?) {
        passengerFlowClient.setConfig(
                deviceSerial,
                objectMapper.writeValueAsString(config.line),
                objectMapper.writeValueAsString(config.direction),
                channelNo
        ).exec()
    }

    override fun getConfig(deviceSerial: String, channelNo: Int?): PassengerFlowConfig {
        return passengerFlowClient.getConfig(
                deviceSerial,
                channelNo
        ).getNonNullData()
    }
}