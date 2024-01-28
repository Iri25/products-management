package ma.android.templateapp.model

import com.google.gson.annotations.SerializedName

data class Entity (
    @field:SerializedName("id")
    var id: Int = 0,
    @field:SerializedName("nume")
    var name: String = "",
    @field:SerializedName("tip")
    var type: String = "",
    @field:SerializedName("cantitate")
    var quantity: Int = 0,
    @field:SerializedName("pret")
    var price: Int = 0,
    @field:SerializedName("discount")
    var discount : Int = 0,
    @field:SerializedName("status")
    var status : Boolean = false
)