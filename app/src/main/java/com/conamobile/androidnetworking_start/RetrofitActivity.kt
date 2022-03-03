package com.conamobile.androidnetworking_start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.conamobile.androidnetworking_start.model.Poster
import com.conamobile.androidnetworking_start.model.PosterResp
import com.conamobile.androidnetworking_start.retrofit.RetrofitHttp
import com.conamobile.androidnetworking_start.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitActivity : AppCompatActivity() {

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        initViews()

    }

    private fun initViews() {
        textView = findViewById(R.id.textView)

//        workWithRetrofitGet()
//        workWithRetrofitPost()
//        workWithRetrofitUpdate()
//        workWithRetrofitDelete()
    }

    //serverdan malumot olib keladi
    fun workWithRetrofitGet() {

        RetrofitHttp.posterService.listPost().enqueue(object : Callback<ArrayList<PosterResp>>{
            override fun onResponse(
                call: Call<ArrayList<PosterResp>>,
                response: Response<ArrayList<PosterResp>>
            ) {
                textView.text = response.body().toString()
                Logger.d("@@@", response.body().toString())
            }

            override fun onFailure(call: Call<ArrayList<PosterResp>>, t: Throwable) {
                textView.text = t.message.toString()
                Logger.d("@@@", t.message.toString())
            }
        })

    }

    //serverga ma'lumot qo'shadi
    fun workWithRetrofitPost(){

        val poster = Poster(1,1,"Nurmuhammad", "Online")

        RetrofitHttp.posterService.createPost(poster).enqueue(object : Callback<PosterResp>{
            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                Logger.d("@@@", response.body().toString())
                textView.text = response.body().toString()
            }

            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                Logger.d("@@@", t.message.toString())
                textView.text = t.message.toString()
            }

        })
    }

    //serverdagi ma'lumotni yangilaydi
    fun workWithRetrofitUpdate(){

        val poster = Poster(1,1,"Nurmuhammad1", "Online1")

        RetrofitHttp.posterService.updatePost(poster.id, poster).enqueue(object : Callback<PosterResp>{
            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                textView.text = response.body().toString()
                Logger.d("@@@", response.body().toString())
            }

            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                textView.text = t.message.toString()
                Logger.d("@@@", t.message.toString())
            }
        })
    }

    //serverdagi ma'lumotni o'chiradi
    fun workWithRetrofitDelete(){
        val poster = Poster(1,1,"Nurmuhammad1", "Online1")
        RetrofitHttp.posterService.deletePost(poster.id).enqueue(object : Callback<PosterResp>{
            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                textView.text = ""+ response.body()
                Logger.d("@@@",""+ response.body())
            }

            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                textView.text = t.message
                Logger.d("@@@", "" + t.message)
            }

        })
    }

}