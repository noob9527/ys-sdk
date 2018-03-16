package cn.staynoob.ys

import cn.staynoob.ys.domain.response.VideoAddress

interface YsService {
    fun listVideoAddress(
            pageStart: Int = 0,
            pageSize: Int = 20
    ): List<VideoAddress>

    fun fetchLimitedAddress(
            deviceSerial: String,
            channelNo: Int = 1,
            expireTime: Int? = null
    ): VideoAddress
}