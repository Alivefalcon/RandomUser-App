package tees.ac.uk.w9637327.randomuserapp.network

import tees.ac.uk.w9637327.randomuserapp.model.UserModel

class RandomService() :RandomServiceInterface
{
    private val randomService: RandomApiServiceInterface = networkrequest.randomService
    override suspend fun getRandomUsers(results: Int): UserModel
    {
        val response = randomService.getRandomUsers()
        if(!response.results.isNotEmpty()){
            return response;
        }
        return response;
    }
}

interface RandomServiceInterface
{
    suspend fun getRandomUsers(
        results: Int = 100
    ): UserModel
}