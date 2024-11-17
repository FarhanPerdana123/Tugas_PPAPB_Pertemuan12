package com.example.pertemuan12

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.pertemuan12.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val channelId = "TEST_NOTIF"
    private val notifId = 90
    private var angkaLike = 0
    private var angkaDislike = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("Counters", Context.MODE_PRIVATE)
        angkaLike = sharedPreferences.getInt("angkaLike", 0)
        angkaDislike = sharedPreferences.getInt("angkaDislike", 0)

        binding.angkaLike.text = angkaLike.toString()
        binding.angakDislike.text = angkaDislike.toString()

        val notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        binding.btnSendNotification.setOnClickListener {
            val notifImage = BitmapFactory.decodeResource(resources, R.drawable.capibara)

            val likeIntent = Intent(this, NotifReceiver::class.java).apply {
                action = "ACTION_LIKE"
            }
            val likePendingIntent = PendingIntent.getBroadcast(
                this, 0, likeIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val dislikeIntent = Intent(this, NotifReceiver::class.java).apply {
                action = "ACTION_DISLIKE"
            }
            val dislikePendingIntent = PendingIntent.getBroadcast(
                this, 1, dislikeIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle("Notifikasi")
                .setContentText("with masbro menuju kesuksesan")
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(notifImage)
                )
                .addAction(R.drawable.like, "Like", likePendingIntent)
                .addAction(R.drawable.dislike, "Dislike", dislikePendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            notifManager.notify(notifId, builder.build())
        }

        updateCounters()

    }

    override fun onResume() {
        super.onResume()
        updateCounters()
    }

    private fun updateCounters() {
        val sharedPreferences = getSharedPreferences("Counters", Context.MODE_PRIVATE)
        angkaLike = sharedPreferences.getInt("angkaLike", 0)
        angkaDislike = sharedPreferences.getInt("angkaDislike", 0)

        binding.angkaLike.text = angkaLike.toString()
        binding.angakDislike.text = angkaDislike.toString()
    }
}