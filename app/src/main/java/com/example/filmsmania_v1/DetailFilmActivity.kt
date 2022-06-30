package com.example.filmsmania_v1

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bellano.themoviedblibrary.network.ApiHelper
import com.squareup.picasso.Picasso


class DetailFilmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)

        val btn_back = findViewById(R.id.btn_back) as Button
        val imageFilmDetail = findViewById(R.id.movie_poster) as ImageView
        val profileName=intent.getIntExtra("idFilm",0)
        val movieData=ApiHelper.getMovie(profileName.toString())
        val movieName= findViewById(R.id.movieName) as TextView
        val movie_TextDescription= findViewById(R.id.movie_TextDescription) as TextView


        movieName.text=movieData?.title.toString()
        movie_TextDescription.text=getSafeSubstring(movieData?.overview.toString(),500)

        Picasso.get().load(ApiHelper.getImageBaseUrl()+movieData?.backdrop_path).into(imageFilmDetail)

        btn_back.setOnClickListener{
            gotoWelcomeActivity()
        }
    }

    private fun gotoWelcomeActivity(){
        this.finish();
    }

    private fun getSafeSubstring(s: String, maxLength: Int): String? {
        if (!TextUtils.isEmpty(s)) {
            if (s.length >= maxLength) {
                return s.substring(0, maxLength)
            }
        }
        return s
    }
}


