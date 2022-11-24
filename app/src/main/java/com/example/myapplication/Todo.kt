package com.example.myapplication

import android.database.Cursor
import android.os.Parcel
import android.os.Parcelable
import com.example.myapplication.DBHelper.Companion.KEY_ID
import com.example.myapplication.DBHelper.Companion.KEY_IS_DONE
import com.example.myapplication.DBHelper.Companion.KEY_TITLE
import com.example.myapplication.DBHelper.Companion.TABLE_NAME

data class Todo(
        val id: Long,
        val title: String,
        val isDone: Boolean
    )

fun getAll(): List<Todo> {
    val result = mutableListOf<Todo>()
    val database = this.writableDatabase
    val cursor: Cursor = database.query(
        TABLE_NAME, null, null, null,
        null, null, null
    )
    if (cursor.moveToFirst()) {
        val idIndex: Int = cursor.getColumnIndex(KEY_ID)
        val titleIndex: Int = cursor.getColumnIndex(KEY_TITLE)
        val isDoneIndex: Int = cursor.getColumnIndex(KEY_IS_DONE)
        do {
            val todo = Todo(
                cursor.getLong(idIndex),
                cursor.getString(titleIndex),
                cursor.getInt(isDoneIndex) == 1
            )
            result.add(todo)
        } while (cursor.moveToNext())
    }
    cursor.close()
    return result
}