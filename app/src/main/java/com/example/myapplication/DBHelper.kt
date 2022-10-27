package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Parcel
import android.os.Parcelable

class DBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), Parcelable {

    // статические константы имеет смысл хранить так:
    companion object {
        // версия БД
        const val DATABASE_VERSION = 1
        // название БД
        const val DATABASE_NAME = "tododb"
        // название таблицы
        const val TABLE_NAME = "todos"
        // названия полей
        const val KEY_ID = "id"
        const val KEY_TITLE = "title"
        const val KEY_IS_DONE = "is_done"
    }


    constructor(parcel: Parcel) : this(TODO("context")) {
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
        CREATE TABLE $TABLE_NAME (
            $KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $KEY_TITLE TEXT NOT NULL,
            $KEY_IS_DONE INTEGER NOT NULL
        )""")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    private companion object CREATOR : Parcelable.Creator<DBHelper> {
        override fun createFromParcel(parcel: Parcel): DBHelper {
            return DBHelper(parcel)
        }

        override fun newArray(size: Int): Array<DBHelper?> {
            return arrayOfNulls(size)
        }
    }
}