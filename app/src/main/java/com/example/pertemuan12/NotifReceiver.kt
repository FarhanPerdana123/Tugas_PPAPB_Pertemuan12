package com.example.pertemuan12

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotifReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        val sharedPreferences = context?.getSharedPreferences("Counters", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()

        when (action) {
            "ACTION_LIKE" -> {
                val currentLike = sharedPreferences?.getInt("angkaLike", 0) ?: 0
                editor?.putInt("angkaLike", currentLike + 1)?.apply()
                Toast.makeText(context, "Liked! Total Likes: ${currentLike + 1}", Toast.LENGTH_SHORT).show()
            }
            "ACTION_DISLIKE" -> {
                val currentDislike = sharedPreferences?.getInt("angkaDislike", 0) ?: 0
                editor?.putInt("angkaDislike", currentDislike + 1)?.apply()
                Toast.makeText(context, "Disliked! Total Dislikes: ${currentDislike + 1}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}