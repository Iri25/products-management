package ma.android.templateapp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import ma.android.templateapp.model.EntityContract

class DatabaseManager(context: Context) {

    private val databaseHelper: DatabaseHelper = DatabaseHelper(context)
    private val sqLiteDatabase: SQLiteDatabase by lazy { databaseHelper.writableDatabase }

    fun insert(values: ContentValues): Long {
        return sqLiteDatabase.insert(EntityContract.TaskEntry.DB_TABLE, "", values)
    }

    fun queryAll(): Cursor {
        return sqLiteDatabase.rawQuery("select * from ${EntityContract.TaskEntry.DB_TABLE}", null)
    }

    fun delete(selection: String, selectionArgs: Array<String>): Int {
        return sqLiteDatabase.delete(EntityContract.TaskEntry.DB_TABLE, selection, selectionArgs)
    }

    fun update(values: ContentValues, selection: String, selectionArgs: Array<String>): Int {
        return sqLiteDatabase.update(EntityContract.TaskEntry.DB_TABLE, values, selection, selectionArgs)
    }

    fun close() {
        sqLiteDatabase.close()
    }
}