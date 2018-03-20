package cn.staynoob.ys.service

import cn.staynoob.ys.domain.response.VideoAddress
import cn.staynoob.ys.http.YsLiveVideoClient
import cn.staynoob.ys.getNonNullData

internal class YsLiveVideoServiceImpl(
        private val liveVideoClient: YsLiveVideoClient
) : YsLiveVideoService {
    override fun listAddress(pageStart: Int, pageSize: Int): List<VideoAddress> {
        return liveVideoClient.listAddress(pageStart, pageSize).getNonNullData()
    }

    override fun fetchLimitedAddress(deviceSerial: String, channelNo: Int, expireTime: Int?): VideoAddress {
        return liveVideoClient.fetchLimitedAddress(deviceSerial, channelNo, expireTime).getNonNullData()
    }
}