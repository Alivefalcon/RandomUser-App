package tees.ac.uk.w9637327.randomuserapp.network

import retrofit2.http.GET
import retrofit2.http.Query
import tees.ac.uk.w9637327.randomuserapp.model.UserModel

interface RandomApiServiceInterface {
    @GET("api")
    suspend fun getRandomUsers(
        @Query("results") results: Int = 50
    ): UserModel
}