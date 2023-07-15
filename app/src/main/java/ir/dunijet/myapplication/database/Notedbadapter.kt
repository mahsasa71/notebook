package ir.dunijet.myapplication.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import ir.dunijet.myapplication.model.Note
import ui.AddNoteActivity

open class Notedbadapter(context: Context?) : Notedatabase(context) {
    fun insertNote(note: Note): Long {
        var db: SQLiteDatabase = writableDatabase
        val contentvalues = ContentValues()
        contentvalues.put(KEY_TITLE, note.title)
        contentvalues.put(KEY_DESCRIPTION, note.description)
        contentvalues.put(KEY_TIME, note.time)
        contentvalues.put(KEY_DATE, note.date)
        return db.insert(TABLE_NAME, null, contentvalues)

    }
    fun editNote(note: Note): Int {
        var db: SQLiteDatabase = writableDatabase
        val contentvalues = ContentValues()
        contentvalues.put(KEY_TITLE, note.title)
        contentvalues.put(KEY_DESCRIPTION, note.description)
        contentvalues.put(KEY_TIME, note.time)
        contentvalues.put(KEY_DATE, note.date)
        return db.update(TABLE_NAME, contentvalues,"id="+note.id,null)

    }
    @SuppressLint("Range")
    fun showallNotes():MutableList<Note>{
        val db:SQLiteDatabase=readableDatabase
        val cursor=db.rawQuery("select * from ${TABLE_NAME}",null)
        var todoslist= mutableListOf<Note>()
        while (cursor.moveToNext()){
            var id:Int=cursor.getInt(0)
            var title:String=cursor.getString(1)
            var description:String=cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))
            var time:String=cursor.getString(cursor.getColumnIndex(KEY_TIME))
            val date:String=cursor.getString(cursor.getColumnIndex(KEY_DATE))
            val note=Note(id,title,description,time,date)
            todoslist.add(note)
        }
        return todoslist
    }
    fun deletall():Int{
        val db:SQLiteDatabase=writableDatabase
        return db.delete(TABLE_NAME,null,null)

    }
    fun deletnote(noteid:Int):Int{
        val db:SQLiteDatabase=writableDatabase
        //return db.delete(TABLE_NAME,"id="+noteid,null)
        var array= listOf<String>(noteid.toString())
        return db.delete(TABLE_NAME,"id=?",array.toTypedArray())
    }
}
