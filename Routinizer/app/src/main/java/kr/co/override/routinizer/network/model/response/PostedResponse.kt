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

data class RankingResponse(
    val users : List<user>
)

data class user(
    val name : String,
    val avatar : String,
    val school : String
)

data class InvitedResponse(
    val participations : List<participation>
)

data class participation(

)

