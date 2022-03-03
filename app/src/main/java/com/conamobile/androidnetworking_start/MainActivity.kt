package com.conamobile.androidnetworking_start

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.conamobile.androidnetworking_start.model.Poster
import com.conamobile.androidnetworking_start.utils.Logger
import com.conamobile.androidnetworking_start.volley.VolleyHandler
import com.example.android_advanced_kotlin.activity.network.volley.VolleyHttp

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var buttonRetroft: Button
    lateinit var poster: Poster

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {
        textView = findViewById(R.id.textView)
        buttonRetroft = findViewById(R.id.retrofyAct)

        buttonRetroft.setOnClickListener {
            val intent = Intent(this, RetrofitActivity::class.java)
            startActivity(intent)
        }

//        workWithVolleyGet()
//        workWithVolleyPost()
//        workWithVolleyPut()
//        workWithVolleyDelete()

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
    fun workWithVolleyPut() {
        poster = Poster(1,1,"Nurmuhammad","Body")
        VolleyHttp.put(VolleyHttp.API_UPDATE_POST + poster.id, VolleyHttp.paramsUpdate(poster), object :
        VolleyHandler{
            override fun onSuccess(response: String?) {
                Logger.d("@@@", response!!)
            }

            override fun onError(error: String?) {
                Logger.d("@@@", error!!)
            }
        })
    }

    //serverdagi ma'lumotni o'chiradi
    fun workWithVolleyDelete(){
        poster = Poster(1,1,"Nurmuhammad","Body")
        VolleyHttp.del(VolleyHttp.API_DELETE_POST + poster.id, object : VolleyHandler{
            override fun onSuccess(response: String?) {
                Logger.d("@@@", response!!)
            }

            override fun onError(error: String?) {
                Logger.d("@@@", error!!)
            }

        })
    }
}