/*
 * Copyright 2026 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.nowinandroid.core.network.retrofit

import com.google.samples.apps.nowinandroid.core.network.model.NetworkNewsResource
import com.google.samples.apps.nowinandroid.core.network.model.NetworkTopic
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit API interface for communicating directly with the Vercel API (jules-api).
 */
interface JulesApiService {
    @GET("api/news")
    suspend fun getNewsResources(
        @Query("id") ids: List<String>? = null,
    ): List<NetworkNewsResource>

    @POST("api/news")
    suspend fun createNewsResource(
        @Body newsResource: NetworkNewsResource,
    ): NetworkNewsResource

    @PUT("api/news/{id}")
    suspend fun updateNewsResource(
        @Path("id") id: String,
        @Body newsResource: NetworkNewsResource,
    ): NetworkNewsResource

    @DELETE("api/news/{id}")
    suspend fun deleteNewsResource(
        @Path("id") id: String,
    )

    @GET("api/topics")
    suspend fun getTopics(
        @Query("id") ids: List<String>? = null,
    ): List<NetworkTopic>

    @POST("api/topics")
    suspend fun createTopic(
        @Body topic: NetworkTopic,
    ): NetworkTopic

    @PUT("api/topics/{id}")
    suspend fun updateTopic(
        @Path("id") id: String,
        @Body topic: NetworkTopic,
    ): NetworkTopic

    @DELETE("api/topics/{id}")
    suspend fun deleteTopic(
        @Path("id") id: String,
    )
}
