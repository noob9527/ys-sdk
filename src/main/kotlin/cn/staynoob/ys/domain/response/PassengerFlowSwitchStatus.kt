package cn.staynoob.ys.domain.response

data class PassengerFlowSwitchStatus(
        val deviceSerial: String,
        val channelNo: Int,
        // 0-close, 1-open
        val enable: Int
)
