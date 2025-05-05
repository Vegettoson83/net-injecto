package com.freenet.injector

import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class ConfigManager {

    private val configFile = File("/data/data/com.freenet.injector/config.ehi")

    fun saveConfig(configData: String) {
        try {
            FileOutputStream(configFile).apply {
                write(configData.toByteArray())
                flush()
                close()
            }
            Log.d("ConfigManager", "Config saved successfully.")
        } catch (e: Exception) {
            Log.e("ConfigManager", "Error saving config", e)
        }
    }

    fun loadConfig(): String? {
        return try {
            FileInputStream(configFile).bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            Log.e("ConfigManager", "Error loading config", e)
            null
        }
    }
}
