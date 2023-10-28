package com.ebelli.core.asset

import android.content.Context
import java.io.InputStream

class AssetHelperImpl(private val context: Context) : AssetHelper {
    override fun open(jsonFile: String): InputStream {
        return context.assets.open(jsonFile)
    }
}