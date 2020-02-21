package com.example.recyclerviewwithdynamicdata

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerviewwithdynamicdata.network.DataParseApi
import com.example.recyclerviewwithdynamicdata.network.DataPropertyParser
import com.example.recyclerviewwithdynamicdata.network.Row
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var loadDataButton: TextView

    private lateinit var listResult: List<Row>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycle_view_layout)

        loadDataButton = findViewById(R.id.textview)
        loadDataButton.setOnClickListener {
            getMarsRealEstateProperties()
        }
    }

    private fun setTextView(value: String) {
        loadDataButton.text = value
    }

    /**
     * Get the data from server and show into text view
     *
     */
    private fun getMarsRealEstateProperties() {

        DataParseApi.retrofitService.getProperties().enqueue(
            object : Callback<DataPropertyParser> {
                override fun onFailure(call: Call<DataPropertyParser>, t: Throwable) {
                    Log.d("RecyclerViewActivity", "Call Fail" + t.message)
                }

                override fun onResponse(
                    call: Call<DataPropertyParser>,
                    response: Response<DataPropertyParser>
                ) {
                    listResult = response.body()!!.rows
                    Log.d("RecyclerViewActivity", response.body().toString())
                    setTextView(response.body()?.title.toString() + " : " + listResult[0].title)

                }
            })
    }
}
