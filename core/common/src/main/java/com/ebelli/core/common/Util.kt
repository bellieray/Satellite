package com.ebelli.core.common

import android.os.Looper
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import com.ebelli.core.asset.AssetHelper
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream

fun EditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow {
        check(Looper.myLooper() == Looper.getMainLooper())
        val listener = doOnTextChanged { text, _, _, _ -> trySend(text) }
        awaitClose { removeTextChangedListener(listener) }
    }.onStart { emit(text) }
}

@OptIn(ExperimentalSerializationApi::class)
inline fun<reified T> AssetHelper.decode(jsonFile: String) : T? =
    this.open(jsonFile)
        .use<InputStream, T>(Json::decodeFromStream)