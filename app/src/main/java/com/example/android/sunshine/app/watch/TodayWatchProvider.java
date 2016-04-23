/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine.app.watch;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.android.sunshine.app.sync.SunshineSyncAdapter;
import com.example.android.sunshine.app.widget.TodayWidgetIntentService;

/**
 * Provider for a horizontally expandable widget showing today's weather.
 *
 * Delegates widget updating to {@link TodayWatchIntentService} to ensure that
 * data retrieval is done on a background thread
 */
public class TodayWatchProvider extends BroadcastReceiver {

    @Override
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        if (SunshineSyncAdapter.ACTION_DATA_UPDATED.equals(intent.getAction())) {
            context.startService(new Intent(context, TodayWatchIntentService.class));
        }
    }
}
