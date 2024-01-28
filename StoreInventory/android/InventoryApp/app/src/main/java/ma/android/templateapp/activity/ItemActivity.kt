package ma.android.templateapp.activity

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_item.*
import ma.android.templateapp.R
import ma.android.templateapp.model.Entity

class ItemActivity : AppCompatActivity() {
    var id = 0
    
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            id = bundle.getInt("MainActId", 0)
            if (id != 0) {
                nameEditText.setText(bundle.getString("MainActTitle"))
            }
        }

        sendButton.setOnClickListener {
            val elementAdapter = EmployeeAdapter(this)

            if (id == 0) {
                val item = Entity(1,"","")
                item.name = nameEditText.text.toString()
                item.type =  typeEditText.text.toString()
                item.price = priceEditText.text.toString().toInt()
                item.quantity = quantityEditText.text.toString().toInt()
                item.discount = discountEditText.text.toString().toInt()
                item.status = statusEditText.text.toString().toBoolean()
                progressBar.visibility = View.VISIBLE


                val handler = Handler()
                handler.postDelayed( {
                    elementAdapter.addEntity(item)
                    elementAdapter.refreshEntities()
                    progressBar.visibility = View.GONE
                    finish()
                }, 1000)
            }
        }
    }
}
