package com.example.dimic.medicaltest.di.module

import com.example.dimic.medicaltest.di.scope.ApplicationScope
import com.example.dimic.medicaltest.data.server.api.TestApi
import com.example.dimic.medicaltest.data.server.api.TestApiConstant
import com.example.dimic.medicaltest.data.server.api.TestApiMethods
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Qualifier

@Module
class TestApiModule {

    @Provides
    @ApplicationScope
    @TestQualifier
    fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
//                    .addInterceptor(ReferralAccessInterceptor(referralUser))
                    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                    .build()


    @Provides
    @ApplicationScope
    @TestQualifier
    fun provideObjectMapper(): ObjectMapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)


    @Provides
    @ApplicationScope
    @TestQualifier
    fun provideConverterFactory(@TestQualifier objectMapper: ObjectMapper): JacksonConverterFactory = JacksonConverterFactory.create(objectMapper)


    @Provides
    @ApplicationScope
    @TestQualifier
    fun provideRetrofit(@TestQualifier client: OkHttpClient, @TestQualifier jacksonConverterFactory: JacksonConverterFactory): Retrofit =
            Retrofit.Builder()
                    .baseUrl(TestApiConstant.BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                    .addConverterFactory(jacksonConverterFactory)
                    .build()


    @Provides
    @ApplicationScope
    fun provideApiMethods(@TestQualifier retrofit: Retrofit): TestApiMethods =
            retrofit.create<TestApiMethods>(TestApiMethods::class.java)


    @Provides
    @ApplicationScope
    fun provideTestApi(methods: TestApiMethods): TestApi = TestApi(methods)

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class TestQualifier