package ma.android.templateapp.networking

import io.reactivex.Completable
import io.reactivex.Observable
import ma.android.templateapp.model.Entity
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RestApi {

    @GET("products") fun getAll(): Observable<List<Entity>>
    @POST("product") fun addEntity(@Body element: Entity): Completable
    companion object {

        fun create(): RestApi {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://10.0.2.2:2025/")
                .build()

            return retrofit.create(RestApi::class.java)
        }
    }
}
