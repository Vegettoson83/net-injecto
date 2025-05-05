package com.freenet.injector

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import net.openvpn.openvpn3.core.Tunnel

class InjectorService : Service() {

    private lateinit var tunnel: Tunnel

    override fun onCreate() {
        super.onCreate()
        Log.d("InjectorService", "VPN Service Created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startTunnel()
        return START_STICKY
    }

    private fun startTunnel() {
        // Set up and start the VPN tunnel
        tunnel = Tunnel()
        tunnel.start()
        Log.d("InjectorService", "VPN Tunnel Started")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        tunnel.stop()
        Log.d("InjectorService", "VPN Tunnel Stopped")
    }
}
