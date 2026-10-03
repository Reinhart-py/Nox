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

import com.google.samples.apps.nowinandroid.core.network.model.LicenseItem
import com.google.samples.apps.nowinandroid.core.network.model.LicenseResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface JulesAdminApi {
    @GET("api/admin/licenses")
    suspend fun getLicenses(
        @Header("x-admin-key") key: String,
    ): LicenseResponse

    @POST("api/admin/licenses")
    suspend fun createLicense(
        @Header("x-admin-key") key: String,
        @Body license: LicenseItem,
    ): LicenseItem

    @PUT("api/admin/licenses/{id}")
    suspend fun updateLicense(
        @Header("x-admin-key") key: String,
        @Path("id") id: String,
        @Body license: LicenseItem,
    ): LicenseItem

    @DELETE("api/admin/licenses/{id}")
    suspend fun deleteLicense(
        @Header("x-admin-key") key: String,
        @Path("id") id: String,
    )
}
