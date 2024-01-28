package ma.android.templateapp.activity

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
import kotlinx.android.synthetic.main.activity_client.*
import kotlinx.android.synthetic.main.content_client.*
import ma.android.templateapp.R
import ma.android.templateapp.adapter.ClientAdapter

class ClientActivity : AppCompatActivity() {
    lateinit var adapter: ClientAdapter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)

        progressBarClient.visibility = View.VISIBLE
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed( {
            adapter = ClientAdapter(this@ClientActivity)
            val layoutManager = LinearLayoutManager(this)
            layoutManager.orientation = LinearLayoutManager.VERTICAL

            listViewContentClient.layoutManager = layoutManager
            listViewContentClient.adapter = adapter
            progressBarClient.visibility = View.GONE
        }, 1000)
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
}
