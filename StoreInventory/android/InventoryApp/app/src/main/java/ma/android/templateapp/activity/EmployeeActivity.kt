package ma.android.templateapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_employee.*
import kotlinx.android.synthetic.main.content_employee.*
import ma.android.templateapp.R

class EmployeeActivity : AppCompatActivity() {
    lateinit var adapter: EmployeeAdapter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)

        progressBarEmployee.visibility = View.VISIBLE
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            adapter = EmployeeAdapter(this@EmployeeActivity)
            val layoutManager = LinearLayoutManager(this)
            layoutManager.orientation = LinearLayoutManager.VERTICAL

            listViewContentEmployee.layoutManager = layoutManager
            listViewContentEmployee.adapter = adapter
            progressBarEmployee.visibility = View.GONE
        }, 1000)


        floatingActionButton.setOnClickListener {
            startActivityForResult(Intent(this@EmployeeActivity, ItemActivity::class.java), 0)


        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.buttons, menu)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refreshButton -> {
                adapter.refreshEntities()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        progressBarEmployee.visibility = View.VISIBLE
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed( {
            adapter = EmployeeAdapter(this)
            adapter.refreshEntities()
            listViewContentEmployee.adapter = adapter

            if (listViewContentEmployee.adapter != null) {
                listViewContentEmployee.adapter!!.notifyDataSetChanged()
            }
            progressBarEmployee.visibility = View.GONE
        }, 1000)
    }

}
