package kr.co.override.routinizer.network.api

import kr.co.override.routinizer.network.model.request.PostingRequest
import kr.co.override.routinizer.network.model.response.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Posted {
    @GET("challenge/hot")
    fun getHotPosts() : Call<PostedResponse>

    @GET("challenge/new")
    fun getNewPosts() : Call<PostedResponse>

    @GET("challenge/exercise")
    fun getExercisePosts() : Call<PostedResponse>

    @GET("auth/ranking")
    fun getAllRanking() : Call<RankingResponse>

    @GET("auth/ranking/grade")
    fun getGradeRanking() : Call<RankingResponse>

    @GET("participate/")
    fun getMyInvitedPost() : Call<PostedResponse>

    @GET("challenge/my")
    fun getMyPost() : Call<PostedResponse>

    @POST("challenge/")
    fun posting(@Body postingRequest: PostingRequest) : Call<PostingResponse>

    @GET("challenge/{id}")
    fun detailPost(@Path("id") id: String): Call<DetailResponse>
}