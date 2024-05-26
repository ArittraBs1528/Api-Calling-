package com.example.apicalling.screen.homescreen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apicalling.R
import com.example.apicalling.adapter.RAdapter
import com.example.apicalling.databinding.ActivityMainBinding


const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class MainActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        loadData()



        viewModel.categoryWiseData.observe(this) {

//            println("Reached here")
            println("Our khela  data" +it.Data[1].Products[0].PictureModels[0].ImageUrl)
            binding.rcv1.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            binding.rcv1.adapter = RAdapter(it.Data)


        }

    }

    private fun loadData() {
        viewModel.getCategoryWiseData()
    }

//    private fun getMydata() {
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val retrofitBuilder = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(BASE_URL)
//            .build()
//            .create(MyApiInterface::class.java)
//
//        val retrofitData = retrofitBuilder.getData()
//
//        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
//            override fun onResponse(
//                p0: Call<List<MyDataItem>?>,
//                response: Response<List<MyDataItem>?>
//            ) {
//
//                var apiDataList = response.body()!!
//
//                val responseBody = response.body()!!
//                val myStringBuilder = StringBuilder()
//                for (myData in responseBody) {
//                    myStringBuilder.append(myData.id)
//                    myStringBuilder.append("\n")
//                    println("My id:" + myData.id)
//
//
//                }
//
//
////                binding.rcv1.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
////                binding.rcv1.adapter = RAdapter(this@MainActivity,apiDataList)
////                binding.textid.text = myStringBuilder
//
//
//            }
//
//            override fun onFailure(p0: Call<List<MyDataItem>?>, p1: Throwable) {
//                Log.d("MainActivity", "OnFailure" + p1.message)
//            }
//        })
//
//    }


}