package com.example.apicalling

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apicalling.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getMydata()

    }

    private fun getMydata() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(MyApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                p0: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {

                var apiDataList = response.body()!!

                val responseBody = response.body()!!
                val myStringBuilder = StringBuilder()
                for (myData in responseBody) {
                    myStringBuilder.append(myData.id)
                    myStringBuilder.append("\n")
                    println("My id:" + myData.id)

                }


                binding.rcv1.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                binding.rcv1.adapter = RAdapter(this@MainActivity,apiDataList)
//                binding.textid.text = myStringBuilder


            }

            override fun onFailure(p0: Call<List<MyDataItem>?>, p1: Throwable) {
                Log.d("MainActivity", "OnFailure" + p1.message)
            }
        })

    }
}