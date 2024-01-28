package ma.android.templateapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ma.android.templateapp.model.EntityContract

private const val SQL_CREATE_ENTRIES =
    """CREATE TABLE IF NOT EXISTS ${EntityContract.TaskEntry.DB_TABLE} (
            ${EntityContract.TaskEntry.COLUMN_ID} INTEGER PRIMARY KEY,
            ${EntityContract.TaskEntry.COLUMN_NAME} TEXT,
            ${EntityContract.TaskEntry.COLUMN_TYPE} TEXT,
            ${EntityContract.TaskEntry.COLUMN_QUANTITY} INTEGER,
            ${EntityContract.TaskEntry.COLUMN_PRICE} INTEGER,
            ${EntityContract.TaskEntry.COLUMN_DISCOUNT} INTEGER,
            ${EntityContract.TaskEntry.COLUMN_STATUS} BOOLEAN
            );
        """

private const val SQL_DELETE_ENTRIES =
    "DROP TABLE IF EXISTS ${EntityContract.TaskEntry.DB_TABLE}"

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(
        context,
        EntityContract.DB_NAME,
        null,
        EntityContract.DB_VERSION
    ) {

    var context: Context? = context

    override fun onCreate(sqLiteDatabase: SQLiteDatabase?) {
        sqLiteDatabase!!.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        sqLiteDatabase!!.execSQL(SQL_DELETE_ENTRIES)
    }
}