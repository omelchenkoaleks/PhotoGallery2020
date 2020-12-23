package com.omelchenkoaleks.photogallery

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

private const val TAG = "PollWorker"

/**
 * Знает как выполнить фоновую работу.
 */
class PollWorker(val context: Context, workerParams: WorkerParameters)
    : Worker(context, workerParams) {

    override fun doWork(): Result {
        Log.i(TAG, "Work request triggered")
        return Result.success()
    }

}