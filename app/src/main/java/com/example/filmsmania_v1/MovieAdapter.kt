package com.example.filmsmania_v1

import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bellano.themoviedblibrary.network.ApiHelper
import com.squareup.picasso.Picasso

const val HEADER_TYPE = 0
const val DIPLOME_TYPE = 1

class MovieAdapter(val data: List<com.bellano.themoviedblibrary.network.models.Movie>?): RecyclerView.Adapter<MovieAdapter.MovieAdapterHolder>() {

    class MovieAdapterHolder(val itemView: View) : RecyclerView.ViewHolder(itemView){
        val title:TextView=itemView.findViewById(R.id.title)
        val descriotion:TextView=itemView.findViewById(R.id.description)
        val imageFilm:ImageView=itemView.findViewById(R.id.imageFilm)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapterHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.film_item,parent,false)
        return MovieAdapterHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapterHolder, position: Int) {
        val current = data?.get(position)
        if (current != null) {
            holder.title.text=current.title
        }
        if (current != null) {
            holder.descriotion.text=getSafeSubstring(current.overview.toString(),150)
        }
        if (current != null) {
            Picasso.get().load(ApiHelper.getImageBaseUrl()+current.backdrop_path).into(holder.imageFilm)
        }
        holder.descriotion.setOnClickListener { view ->
            val intent= Intent (view.context, DetailFilmActivity::class.java)
            data?.get(position)?.id?.let {
                intent.putExtra("idFilm",it)}
            view.context.startActivity(intent)
        }
    }
    private fun getSafeSubstring(s: String, maxLength: Int): String? {
        if (!TextUtils.isEmpty(s)) {
            if (s.length >= maxLength) {
                return s.substring(0, maxLength)
            }
        }
        return s
    }
    override fun getItemCount(): Int = data?.size ?: 0

}
interface DisplayItem
data class HeaderItem(val title: String) : DisplayItem

