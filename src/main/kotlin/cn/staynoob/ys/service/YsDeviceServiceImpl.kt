package cn.staynoob.ys.service

import cn.staynoob.ys.domain.response.Picture
import cn.staynoob.ys.getNonNullData
import cn.staynoob.ys.http.YsDeviceClient

internal class YsDeviceServiceImpl(
        private val ysDeviceClient: YsDeviceClient
) : YsDeviceService {
    override fun capture(deviceSerial: String, channelNo: Int): Picture {
        return ysDeviceClient.capture(deviceSerial, channelNo)
                .getNonNullData()
    }
}