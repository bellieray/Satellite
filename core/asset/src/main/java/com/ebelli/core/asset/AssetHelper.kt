package com.ebelli.core.asset

import java.io.InputStream

interface AssetHelper{
    fun open(jsonFile :String) : InputStream
}