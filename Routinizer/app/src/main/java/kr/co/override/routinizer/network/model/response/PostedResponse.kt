package kr.co.override.routinizer.network.model.response

data class PostedResponse(
    val challenges : List<challenge>
)

data class challenge(
    val id: String,
    val title: String,
    val image: String,
    val category: String,
    val participantCount: Int
)
