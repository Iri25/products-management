package ma.android.templateapp.model

import android.provider.BaseColumns

object EntityContract {
    const val DB_NAME = "inventory_database"
    const val DB_VERSION = 1

    object TaskEntry : BaseColumns {
        const val DB_TABLE = "product_table"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_TYPE = "type"
        const val COLUMN_QUANTITY = "quantity"
        const val COLUMN_PRICE = "price"
        const val COLUMN_DISCOUNT = "discount"
        const val COLUMN_STATUS = "status"

    }
}