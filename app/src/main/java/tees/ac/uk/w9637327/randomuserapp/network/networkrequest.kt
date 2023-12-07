package tees.ac.uk.w9637327.randomuserapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object networkrequest {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val randomService: RandomApiServiceInterface = retrofit.create(RandomApiServiceInterface::class.java)
}