package com.example.filmsmania_v1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.bellano.themoviedblibrary.network.ApiHelper
import com.example.filmsmania_v1.databinding.ActivityWelcomeBinding


class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityWelcomeBinding = DataBindingUtil.setContentView(this,R.layout.activity_welcome)
        binding.filmList.layoutManager = GridLayoutManager (this,1) // LinearLayoutManager(this)
        binding.filmList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        val topRated = ApiHelper.getPopularMovies()
        topRated?.results
        binding.filmList.adapter = MovieAdapter(topRated?.results)
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.search.clearFocus()
                val searchR = query?.let { ApiHelper.searchMovie(it) }
                binding.filmList.adapter = MovieAdapter(searchR?.results)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        //val searchfilms_btn = findViewById(R.id.searchfilms_btn) as Button
    /*
        searchfilms_btn.setOnClickListener {
            gotoSearchActivity();
        }
     */
    }


    private fun gotoDetailFilm(id : Int){
        val intent= Intent (this, DetailFilmActivity::class.java)
        intent.putExtra("idFilm",id)
        this.startActivity(intent)
    }

    private fun gotoSearchActivity(){
        val intent= Intent (this, SearchActivity::class.java)
        this.startActivity(intent)
    }



}