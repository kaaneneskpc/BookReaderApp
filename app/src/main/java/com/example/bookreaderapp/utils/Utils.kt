package com.example.bookreaderapp.utils

import android.icu.text.DateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.Timestamp

@RequiresApi(Build.VERSION_CODES.N)
fun formatDate(timestamp: Timestamp): String {
    return DateFormat.getDateInstance().format(timestamp.toDate()).toString().split(",")[0]
}