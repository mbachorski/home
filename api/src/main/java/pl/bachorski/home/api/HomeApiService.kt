package pl.bachorski.home.api

import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.bachorski.domain.Room
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private const val HC_USER_LOGIN = BuildConfig.HC_USER_LOGIN
private const val HC_USER_PASSWORD = BuildConfig.HC_USER_PASSWORD
private const val BASE_URL = BuildConfig.HC_IP

private val loggingInterceptor =
    HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY }


private val httpClient = OkHttpClient.Builder()
    .addInterceptor(BasicAuthInterceptor(HC_USER_LOGIN, HC_USER_PASSWORD))
    .addInterceptor(loggingInterceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(httpClient)
    .build()


interface HomeApiService {

    @GET("devices")
    suspend fun getDevices(): List<JsonObject>

    @GET("rooms")
    suspend fun getRooms(): List<Room>
}

object HomeApiRetrofitService {
    val homeApiService: HomeApiService by lazy { retrofit.create(HomeApiService::class.java) }
}

interface DevicesApiService {

    @POST("devices/{deviceId}/action/turnOn")
    suspend fun turnOn(@Path("deviceId") deviceId: Int)

    @POST("devices/{deviceId}/action/turnOff")
    suspend fun turnOff(@Path("deviceId") deviceId: Int)
}

object DevicesApiRetrofitService {
    val devicesApiService: DevicesApiService by lazy { retrofit.create(DevicesApiService::class.java) }
}