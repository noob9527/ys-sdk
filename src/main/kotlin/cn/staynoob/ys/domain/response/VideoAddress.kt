package cn.staynoob.ys.domain.response

import java.time.Instant

/**
 * example:
 * "deviceSerial": "440912222",
 * "channelNo": 1,
 * "liveAddress": "http://open.ys7.com/openlive/temp.m3u8?t=8Yh6jnVwyq6-RU33oaX3nEogqryuNBU2P1Qqce-dT6V-TsLRWj-7kPVukT8lIlvm",
 * "hdAddress": "http://open.ys7.com/openlive/temp.hd.m3u8?t=8Yh6jnVwyq6-RU33oaX3nEogqryuNBU2P1Qqce-dT6V-TsLRWj-7kPVukT8lIlvm",
 * "rtmp": null,
 * "rtmpHd": null,
 * "status": 1,
 * "exception": 0,
 * "beginTime": 1484038680939,
 * "endTime": 1484038980939
 */
data class VideoAddress(
        val deviceSerial: String,
        val channelNo: Int,
        val liveAddress: String?,
        val hdAddress: String?,
        val rtmp: String?,
        val rtmpHd: String?,
        val beginTime: Instant,
        val endTime: Instant,
        val status: Int,
        val exception: Int
)
