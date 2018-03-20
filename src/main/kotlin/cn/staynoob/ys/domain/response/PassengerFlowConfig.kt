package cn.staynoob.ys.domain.response

import cn.staynoob.ys.domain.Segment

data class PassengerFlowConfig(
        val line: Segment,
        val direction: Segment
)
