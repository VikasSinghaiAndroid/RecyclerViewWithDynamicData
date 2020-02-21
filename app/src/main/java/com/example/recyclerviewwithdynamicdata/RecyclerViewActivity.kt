/**
 * Main Class form where application starts
 */
package com.example.recyclerviewwithdynamicdata

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithdynamicdata.adapter.RecycleViewAdapter
import com.example.recyclerviewwithdynamicdata.network.DataParseApi
import com.example.recyclerviewwithdynamicdata.network.DataPropertyParser
import com.example.recyclerviewwithdynamicdata.network.Row
import kotlinx.android.synthetic.main.recycle_view_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var adapter: RecycleViewAdapter

    private lateinit var loadDataButton: Button

    private lateinit var listResult: List<Row>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycle_view_layout)

        // setting up action bar title
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setActionBar(RecyclerViewActivity::class.java.simpleName)
        }

        //getting recyclerView from xml
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        //adding a layout manager
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        //Button to load the data from server and show in recycle view
        this.loadDataButton = findViewById(R.id.load_data)
        this.loadDataButton.setOnClickListener {
            getData()
        }

        // Set the colors for Pull To Refresh View
        items_swipe_to_refresh.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                this,
                R.color.colorPrimary
            )
        )
        items_swipe_to_refresh.setColorSchemeColors(Color.WHITE)

        //List refresh listener
        items_swipe_to_refresh.setOnRefreshListener {
            getData()
        }

    }

    /**
     * Set the action bar
     *
     * @param name
     */
    private fun setActionBar(name: String) {
        supportActionBar!!.title = name
    }

    /**
     * Get the data from server and show into text view
     *
     */
    private fun getData() {
        listResult = emptyList()
        DataParseApi.retrofitService.getProperties().enqueue(
            object : Callback<DataPropertyParser> {
                override fun onFailure(call: Call<DataPropertyParser>, t: Throwable) {
                    Log.d("RecyclerViewActivity", "Call Fail" + t.message)
                    //enabling the button to load the data
                    loadDataButton.isClickable = true
                }

                override fun onResponse(
                    call: Call<DataPropertyParser>,
                    response: Response<DataPropertyParser>
                ) {
                    listResult = response.body()!!.rows

                    //set action bar from json data
                    response.body()!!.title?.let { setActionBar(it) }

                    //creating our adapter
                    adapter =
                        RecycleViewAdapter(
                            listResult
                        )

                    //now adding the adapter to recycler view
                    recyclerView.adapter = adapter

                    loadDataButton.isClickable = false

                    items_swipe_to_refresh.isRefreshing = false

                }
            })
    }
}
