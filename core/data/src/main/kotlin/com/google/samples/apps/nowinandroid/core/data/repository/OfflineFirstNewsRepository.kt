/*
 * Copyright 2022 The Android Open Source Project
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

package com.google.samples.apps.nowinandroid.core.data.repository

import com.google.samples.apps.nowinandroid.core.data.Synchronizer
import com.google.samples.apps.nowinandroid.core.datastore.NiaPreferencesDataSource
import com.google.samples.apps.nowinandroid.core.model.data.NewsResource
import com.google.samples.apps.nowinandroid.core.network.NiaNetworkDataSource
import com.google.samples.apps.nowinandroid.core.network.model.asExternalModel
import com.google.samples.apps.nowinandroid.core.notifications.Notifier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation of [NewsRepository] using network data.
 */
internal class OfflineFirstNewsRepository @Inject constructor(
    private val niaPreferencesDataSource: NiaPreferencesDataSource,
    private val network: NiaNetworkDataSource,
    private val notifier: Notifier,
) : NewsRepository {

    override fun getNewsResources(
        query: NewsResourceQuery,
    ): Flow<List<NewsResource>> = flow {
        val networkNewsResources = network.getNewsResources(ids = query.filterNewsIds?.toList())
        val topics = network.getTopics().associateBy { it.id }
        val newsResources = networkNewsResources.map { netNews ->
            NewsResource(
                id = netNews.id,
                title = netNews.title,
                content = netNews.content,
                url = netNews.url,
                headerImageUrl = netNews.headerImageUrl,
                publishDate = netNews.publishDate,
                type = netNews.type,
                topics = netNews.topics.mapNotNull { topics[it]?.asExternalModel() },
            )
        }.filter { news ->
            if (query.filterTopicIds != null) {
                news.topics.any { topic -> query.filterTopicIds.contains(topic.id) }
            } else {
                true
            }
        }
        emit(newsResources)
    }

    override suspend fun syncWith(synchronizer: Synchronizer): Boolean = true
}
