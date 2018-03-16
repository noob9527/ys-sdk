package cn.staynoob.ys

import cn.staynoob.ys.domain.response.VideoAddress
import cn.staynoob.ys.http.YsApiService

internal class YsServiceImpl(
        private val ysApiService: YsApiService
) : YsService {
    override fun listVideoAddress(pageStart: Int, pageSize: Int): List<VideoAddress> {
        return ysApiService.listVideoAddress(pageStart, pageSize).toResult()
    }

    override fun fetchLimitedAddress(deviceSerial: String, channelNo: Int, expireTime: Int?): VideoAddress {
        return ysApiService.fetchLimitedAddress(deviceSerial, channelNo, expireTime).toResult()
    }
}