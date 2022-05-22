package com.example.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieScreen1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_screen1)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclevi)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = GridLayoutManager(this,2)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemsViewModel(androidx.appcompat.R.drawable.abc_ic_menu_overflow_material, "Item " + i))
        }


        // Interface
        val apiInterface = ApiInterface.create().getMovies("7c2f89efbd716ac9588de5bf16b686c2")

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue( object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>?, response: Response<Movie>?) {
              Log.d("testlog1","toSucces:${call.toString()}${response?.body()?.results}")

                val adapter = CustomAdapter(response?.body()?.results)

                recyclerview.adapter = adapter
            }


            override fun onFailure(call: Call<Movie>?, t: Throwable?) {
            Log.d("testlog1","Fail:${t?.message}")
            }
        })
    }


    // close app using back button
    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }
}