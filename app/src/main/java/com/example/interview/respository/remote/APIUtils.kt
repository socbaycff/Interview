package com.example.interview.respository.remote


class APIUtils  {
    companion object {
        val baseURL = "https://gist.githubusercontent.com"
        fun getRoomService(): RoomService {
            return RetrofitClient.getClient(baseURL)
                .create(RoomService::class.java)

        }
    }
}