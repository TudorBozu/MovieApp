package com.example.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetails : AppCompatActivity() {

    // declaram variabile
    private  lateinit var title: TextView
    private lateinit var over:TextView
    private  lateinit var releasedate:TextView
    private lateinit var  score: TextView
    private lateinit var banner:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        val id = intent.getIntExtra("id",0)

        title = findViewById(R.id.movie_details_text)
        releasedate = findViewById(R.id.movie_details_text)
        score = findViewById(R.id.Movie_relese_score)
        over = findViewById(R.id.movietext1_details)
        banner=findViewById(R.id.imagemovie)


        val apiInterface = id?.let { ApiInterface.create().getMovieDetails(it,"7c2f89efbd716ac9588de5bf16b686c2") }
        apiInterface?.enqueue(object:Callback<MovieDat>{
            override fun onResponse(call:Call<MovieDat>?, response:Response<MovieDat>?){
                title.text=response?.body()?.title
                over.text=response?.body()?.overview
                releasedate.text=response?.body()?.release_date.toString()
                score.text=response?.body()?. vote_average.toString()
                Picasso.get().load("https://image.tmdb.org/t/p/w500"+response?.body()?.backdrop_path).into(banner)

    }

            override fun onFailure(call: Call<MovieDat>, t: Throwable) {
                Log.d("test","onFail${t.message}")
            }
        })
    }
}