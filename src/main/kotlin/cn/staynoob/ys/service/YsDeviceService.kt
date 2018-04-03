package cn.staynoob.ys.service

import cn.staynoob.ys.domain.response.Picture

interface YsDeviceService {
    fun capture(
            deviceSerial: String,
            channelNo: Int = 1
    ): Picture
}