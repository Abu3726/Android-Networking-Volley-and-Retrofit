package com.conamobile.androidnetworking_start

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.conamobile.androidnetworking_start.adapter.PosterAdapter
import com.conamobile.androidnetworking_start.model.Poster
import com.conamobile.androidnetworking_start.utils.Logger
import com.conamobile.androidnetworking_start.volley.VolleyHandler
import com.example.android_advanced_kotlin.activity.network.volley.VolleyHttp
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var buttonRetroft: Button

    //    lateinit var poster: Poster
    lateinit var recyclerView: RecyclerView
    var posters = ArrayList<Poster>()
//    lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {
        textView = findViewById(R.id.textView)
        buttonRetroft = findViewById(R.id.retrofyAct)
        recyclerView = findViewById(R.id.recycleView)
        val b_floating: FloatingActionButton = findViewById(R.id.b_floating)
//        progress = findViewById(R.id.progressBar)

        buttonRetroft.setOnClickListener {
            val intent = Intent(this, RetrofitActivity::class.java)
            startActivity(intent)
        }

        apiPosterList()

        workWithVolleyGet()
//        workWithVolleyPost()
//        workWithVolleyPut()
//        workWithVolleyDelete()

    }

    private fun apiPosterList() {
        //
        VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(), object : VolleyHandler {
            override fun onSuccess(response: String?) {
                val postArray = Gson().fromJson(response, Array<Poster>::class.java)

                posters.clear()
                posters.addAll(postArray)
//                for (poster in postArray) {
//                    posters.add(poster)
//                }

                refreshAdapter(posters)
                Log.d("@@@onResponse ", "" + posters.size)
            }

            override fun onError(error: String?) {
                Log.d("@@@", error!!)
            }
        })
    }

    private fun refreshAdapter(posters: ArrayList<Poster>) {
        val adapter = PosterAdapter(this, posters)
        recyclerView.adapter = adapter
    }

    fun dialogPoster(poster: Poster?) {
        AlertDialog.Builder(this)
            .setTitle("Delete Poster")
            .setMessage("Are you sure you want to delete this poster?")
            .setPositiveButton(
                android.R.string.yes
            ) {
                    dialog, which -> apiPosterDelete(poster!!)
//                progress.visibility = View.VISIBLE
            }
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    private fun apiPosterDelete(poster: Poster) {

        VolleyHttp.del(VolleyHttp.API_DELETE_POST + poster.id,
            object : VolleyHandler {
            override fun onSuccess(response: String?) {
                Log.d("@@@",response!!)
//                progress.visibility = View.GONE
                apiPosterList()
            }

            override fun onError(error: String?) {
                Log.d("@@@",error!!)
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    //serverdan malumot olib keladi
    fun workWithVolleyGet() {
        VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(), object : VolleyHandler {
            override fun onSuccess(response: String?) {
                Logger.d("VolleyHttp", response!!)
                textView.text = response
            }

            override fun onError(error: String?) {
                Logger.d("VolleyHttp", error!!)
                textView.text = error
            }

        })
    }

    //serverga malumot yuboradi
    fun workWithVolleyPost() {
        val poster = Poster(1, 1, "PDP", "Online")
        VolleyHttp.post(VolleyHttp.API_CREATE_POST, VolleyHttp.paramsCreate(poster), object :
            VolleyHandler {
            override fun onSuccess(response: String?) {
                Logger.d("@@@", response!!)
                textView.text = response
            }

            override fun onError(error: String?) {
                Logger.d("@@@", error!!)
                textView.text = error
            }
        })
    }

    //serverdagi ma'lumotni yangilaydi
//    fun workWithVolleyPut() {
//        poster = Poster(1,1,"Nurmuhammad","Body")
//        VolleyHttp.put(VolleyHttp.API_UPDATE_POST + poster.id, VolleyHttp.paramsUpdate(poster), object :
//        VolleyHandler{
//            override fun onSuccess(response: String?) {
//                Logger.d("@@@", response!!)
//            }
//
//            override fun onError(error: String?) {
//                Logger.d("@@@", error!!)
//            }
//        })
//    }

    //serverdagi ma'lumotni o'chiradi
//    fun workWithVolleyDelete(){
//        poster = Poster(1,1,"Nurmuhammad","Body")
//        VolleyHttp.del(VolleyHttp.API_DELETE_POST + poster.id, object : VolleyHandler{
//            override fun onSuccess(response: String?) {
//                Logger.d("@@@", response!!)
//            }
//
//            override fun onError(error: String?) {
//                Logger.d("@@@", error!!)
//            }
//
//        })
//    }
}