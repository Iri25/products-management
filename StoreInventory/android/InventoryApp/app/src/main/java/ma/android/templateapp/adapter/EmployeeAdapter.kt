package ma.android.templateapp.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_view_employee.view.*
import ma.android.templateapp.R
import ma.android.templateapp.model.Entity
import ma.android.templateapp.networking.RestApi
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException

@RequiresApi(Build.VERSION_CODES.M)
class EmployeeAdapter(val context: Context): RecyclerView.Adapter<EmployeeAdapter.EntityViewAdapter>(){

    private val employee by lazy { RestApi.create() }
    private var entitiesList: ArrayList<Entity> = ArrayList()

    init { refreshEntities() }

    class EntityViewAdapter(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EntityViewAdapter {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_employee, parent, false)

        return EntityViewAdapter(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: EntityViewAdapter, position: Int) {

        holder.view.idProductViewEmployee.text = entitiesList[position].id.toString()
        holder.view.nameProductViewEmployee.text = entitiesList[position].name
        holder.view.typeProductViewEmployee.text = entitiesList[position].type
        holder.view.priceProductViewEmployee.text = entitiesList[position].price.toString()
        holder.view.quantityProductViewEmployee.text = entitiesList[position].quantity.toString()
    }

    override fun getItemCount() = entitiesList.size

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("CheckResult", "NotifyDataSetChanged")
    fun refreshEntities() {
        if (checkOnline()) {
            employee.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        entitiesList.clear()
                        entitiesList.addAll(result)
                        entitiesList.sortWith(compareBy({ it.id }, { it.name }))
                        notifyDataSetChanged()
                        Log.d("Entities -> ", entitiesList.toString())
                    },
                    { throwable ->
                        if (throwable is HttpException) {
                            val body: ResponseBody = throwable.response()?.errorBody()!!
                            Toast.makeText(
                                context,
                                "Error: ${JSONObject(body.string()).getString("text")}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                )
        }
        else {
            Toast.makeText(context, "Not online!", Toast.LENGTH_LONG).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkOnline(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            else -> false
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("CheckResult")
    fun addEntity(element: Entity) {
        if (checkOnline()) {
            employee.addEntity(element)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        refreshEntities()
                        Log.d("Entity added -> ", element.toString())
                    },
                    { throwable ->
                        if (throwable is HttpException) {
                            val body: ResponseBody = throwable.response()?.errorBody()!!
                            Toast.makeText(
                                context,
                                "Error: ${JSONObject(body.string()).getString("text")}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                )
        }
        else {
            Toast.makeText(context, "Not online!", Toast.LENGTH_LONG).show()
        }
    }
}