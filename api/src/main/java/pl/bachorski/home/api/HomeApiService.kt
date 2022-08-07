package pl.bachorski.home.api

import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val HC_USER_LOGIN = BuildConfig.HC_USER_LOGIN
private const val HC_USER_PASSWORD = BuildConfig.HC_USER_PASSWORD
private const val BASE_URL = BuildConfig.HC_IP

private val httpClient = OkHttpClient.Builder()
    .addInterceptor(BasicAuthInterceptor(HC_USER_LOGIN, HC_USER_PASSWORD))
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(httpClient)
    .build()


interface HomeApiService {

    @GET("devices")
    suspend fun getDevices(): List<JsonObject>
}

object HomeApiRetrofitService {
    val retrofitService: HomeApiService by lazy { retrofit.create(HomeApiService::class.java) }
}