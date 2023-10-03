/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.firebase.sessions

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.google.firebase.sessions.provider.FirebaseSessionsProvider
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class FirebaseSessionsTests {
  @Before
  fun setUp() {
    Firebase.initialize(
      ApplicationProvider.getApplicationContext(),
      FirebaseOptions.Builder()
        .setApplicationId(APP_ID)
        .setApiKey(API_KEY)
        .setProjectId(PROJECT_ID)
        .build()
    )
  }

  @After
  fun cleanUp() {
    FirebaseApp.clearInstancesForTest()
  }

  @Test
  fun firebaseSessionsDoesInitialize() {
    assertThat(FirebaseSessions.instance).isNotNull()
  }

  @Test
  fun firebaseSessionsProvider_isColdStarts_returnsTrueExactlyOnce() {
    // The first time we check, it's a cold start.
    assertThat(FirebaseSessionsProvider.isColdStart()).isTrue()

    // Every check after, it's not a cold start.
    assertThat(FirebaseSessionsProvider.isColdStart()).isFalse()
    assertThat(FirebaseSessionsProvider.isColdStart()).isFalse()
  }

  companion object {
    private const val APP_ID = "1:1:android:1a"
    private const val API_KEY = "API-KEY-API-KEY-API-KEY-API-KEY-API-KEY"
    private const val PROJECT_ID = "PROJECT-ID"
  }
}
