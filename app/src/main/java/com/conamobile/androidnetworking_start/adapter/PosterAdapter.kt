package com.conamobile.androidnetworking_start.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.conamobile.androidnetworking_start.MainActivity
import com.conamobile.androidnetworking_start.R
import com.conamobile.androidnetworking_start.model.Poster

class PosterAdapter(var activity: MainActivity, var items: ArrayList<Poster>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_item, parent, false)
        return PosterViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val poster: Poster = items[position]
        if (holder is PosterViewHolder) {
            val ll_poster = holder.ll_poster
            ll_poster.setOnLongClickListener {
                activity.dialogPoster(poster)
                false
            }
            val tv_title = holder.tv_title
            val tv_body = holder.tv_body
            tv_title.setText(poster.title.toUpperCase())
            tv_body.setText(poster.body)
        }
    }

    inner class PosterViewHolder(view: View) : RecyclerView.ViewHolder(
        view
    ) {
        var ll_poster: ConstraintLayout
        var tv_title: TextView
        var tv_body: TextView

        init {
            ll_poster = view.findViewById(R.id.ll_poster)
            tv_title = view.findViewById(R.id.tv_title)
            tv_body = view.findViewById(R.id.tv_body)
        }
    }

    init {
        this.activity = activity
        this.items = items
    }
}