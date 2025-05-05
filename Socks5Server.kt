package com.freenet.injector

import android.util.Log
import java.net.ServerSocket
import java.net.Socket

class Socks5Server(private val port: Int = 1080) {

    fun start() {
        Thread {
            try {
                val serverSocket = ServerSocket(port)
                Log.d("Socks5Server", "SOCKS5 Server started on port $port")
                while (true) {
                    val clientSocket: Socket = serverSocket.accept()
                    handleClient(clientSocket)
                }
            } catch (e: Exception) {
                Log.e("Socks5Server", "Error starting SOCKS5 server", e)
            }
        }.start()
    }

    private fun handleClient(clientSocket: Socket) {
        try {
            // Handle SOCKS5 protocol (basic forward)
            val clientOutput = clientSocket.getOutputStream()
            val clientInput = clientSocket.getInputStream()

            // Just forwarding data for now
            clientInput.copyTo(clientOutput)
            clientSocket.close()

            Log.d("Socks5Server", "Forwarded data from client.")
        } catch (e: Exception) {
            Log.e("Socks5Server", "Error handling SOCKS5 client", e)
        }
    }
}
