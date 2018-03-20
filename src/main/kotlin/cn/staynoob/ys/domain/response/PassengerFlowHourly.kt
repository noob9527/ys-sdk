package cn.staynoob.ys.domain.response

data class PassengerFlowHourly(
        val hourIndex: Int,
        val inFlow: Int,
        val outFlow: Int
)