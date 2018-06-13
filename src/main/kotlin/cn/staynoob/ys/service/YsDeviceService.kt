package cn.staynoob.ys.service

import cn.staynoob.ys.domain.response.Picture

interface YsDeviceService {

    fun addDevice(
            deviceSerial: String,
            validateCode: String
    )

    fun turnOffEncrypt(
            deviceSerial: String,
            validateCode: String
    )

    fun capture(
            deviceSerial: String,
            channelNo: Int = 1
    ): Picture
}