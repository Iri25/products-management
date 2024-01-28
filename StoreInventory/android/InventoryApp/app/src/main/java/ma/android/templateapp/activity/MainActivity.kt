package ma.android.templateapp.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ma.android.templateapp.R

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        employeeButton.setOnClickListener { openEmployee() }

        clientButton.setOnClickListener { openClient() }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun openEmployee() {
        startActivity(Intent(this, EmployeeActivity::class.java))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun openClient() {
        if (checkOnline()) {
            startActivity(Intent(this, ClientActivity::class.java))
        }
        else {
            Toast.makeText(this, "There are no internet connections!", Toast.LENGTH_LONG).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkOnline(): Boolean {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            else -> false
        }
    }
}