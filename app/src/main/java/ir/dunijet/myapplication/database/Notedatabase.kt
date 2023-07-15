package ir.dunijet.myapplication.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class Notedatabase(context: Context?) : SQLiteOpenHelper(context,  "Note.dp",null, 1) {

        val TABLE_NAME: String = "tbl_note"
        val KEY_ID: String = "id"
        val KEY_TITLE: String = "note"
        val KEY_DESCRIPTION: String = "description"
        val KEY_TIME: String = "time_note"
        val KEY_DATE: String = "date_note"

    override fun onCreate(db: SQLiteDatabase?) {
        val query="create table ${TABLE_NAME}(${KEY_ID} Integer PRIMARY KEY AUTOINCREMENT,${KEY_TITLE} varchar(100), " +
                "${KEY_DESCRIPTION} Text,${KEY_TIME} varchar(50),${KEY_DATE} varchar(50))"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

}