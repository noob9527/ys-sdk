package cn.staynoob.ys.service

import cn.staynoob.ys.domain.response.VideoAddress

interface YsLiveVideoService {
    fun listAddress(
            pageStart: Int = 0,
            pageSize: Int = 20
    ): List<VideoAddress>

    fun fetchLimitedAddress(
            deviceSerial: String,
            channelNo: Int = 1,
            expireTime: Int? = null
    ): VideoAddress
}