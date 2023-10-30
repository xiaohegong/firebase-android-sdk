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

package com.google.firebase.testing.sessions

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.os.RemoteException
import android.util.Log
import android.widget.Button

/** Second activity from the MainActivity that runs on a different process. */
class SecondActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_second)
    findViewById<Button>(R.id.prev_activity_button).setOnClickListener {
      val intent = Intent(this, MainActivity::class.java)
      intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
      startActivity(intent)
    }
    findViewById<Button>(R.id.kill_background_processes).setOnClickListener {
      getSystemService(ActivityManager::class.java).killBackgroundProcesses("com.google.firebase.testing.sessions")
    }
  }
}
