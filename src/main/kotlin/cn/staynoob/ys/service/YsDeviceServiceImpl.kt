package cn.staynoob.ys.service

import cn.staynoob.ys.domain.response.Picture
import cn.staynoob.ys.exec
import cn.staynoob.ys.getNonNullData
import cn.staynoob.ys.http.YsDeviceClient

internal class YsDeviceServiceImpl(
        private val ysDeviceClient: YsDeviceClient
) : YsDeviceService {
    override fun addDevice(deviceSerial: String, validateCode: String) {
        ysDeviceClient.addDevice(deviceSerial, validateCode).exec()
    }

    override fun capture(deviceSerial: String, channelNo: Int): Picture {
        return ysDeviceClient.capture(deviceSerial, channelNo)
                .getNonNullData()
    }
}