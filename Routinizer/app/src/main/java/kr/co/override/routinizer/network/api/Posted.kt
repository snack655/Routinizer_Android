package kr.co.override.routinizer.network.api

import kr.co.override.routinizer.network.model.response.PostedResponse
import kr.co.override.routinizer.network.model.response.RankingResponse
import retrofit2.Call
import retrofit2.http.GET

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

    @GET("participation/")
    fun getMyInvitedPost() : Call<>
}