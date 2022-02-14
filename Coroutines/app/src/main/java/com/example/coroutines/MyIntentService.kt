package com.example.coroutines

import android.app.IntentService
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.Context
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.pow

// TODO: Rename actions, choose action names that describe tasks that this
// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
private const val ACTION_NUMBER = "com.example.coroutines.action.NUMBER"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.

 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.

 */
class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_NUMBER -> {
                handleActionNUMBER()
            }
        }
    }
    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionNUMBER() {
        GlobalScope.launch {
            val channel = Channel<Int>(10) // отсюда можно читать и сюда можно передавать
            val number = 2.0
            var n = 1.0
            var res = 0.0
            while (!n.equals(10.0E9)) {
                res += number.pow(n)
                delay(3000)
                n++
                launch {
                    listOf(res).forEach { channel.send(it.toInt()) }
                }
            }
        }
        val intent = Intent("")
        //intent.putExtra(RESULT, res)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }


    companion object {
        /**
         * Starts this service to perform action Foo with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun startActionNUMBER(context: Context) {
            val intent = Intent(context, MyIntentService::class.java).apply {
                action = ACTION_NUMBER
                //putExtra(EXTRA_PARAM1, param1)
                //putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }
        const val RESULT = "RESULT"
    }
}