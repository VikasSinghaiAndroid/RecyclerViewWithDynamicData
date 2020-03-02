package com.example.samplewithall.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samplewithall.R
import com.example.samplewithall.models.Row
import com.example.samplewithall.ui.adapter.DataListAdapter
import com.example.samplewithall.utils.ViewModelProviderFactory
import com.example.samplewithall.viewmodels.MainActivityViewModel
import com.log4k.d
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ListFragment : DaggerFragment(), DataListAdapter.Interaction {

    private var dataListAdapter: DataListAdapter? = null

    private lateinit var mainActivityViewModel: MainActivityViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var dataList: List<Row>

    private var title: String? = "No Title"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        initRecyclerView()
        observerLiveData()
        setUpPullDownRefresh()
    }

    private fun setUpPullDownRefresh() {
        // Set the colors for Pull To Refresh View
        items_swipe_to_refresh.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                requireActivity(),
                R.color.colorPrimary
            )
        )
        items_swipe_to_refresh.setColorSchemeColors(Color.WHITE)

        //List refresh listener
        items_swipe_to_refresh.setOnRefreshListener {
            observerLiveData()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataList = mutableListOf()
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    private fun observerLiveData() {
        d("Inside observerLiveData")
        dataList.isEmpty()
        val dataFromServer = mainActivityViewModel.getDataFromServer()
        if (dataFromServer == null) progressBar?.visibility = GONE
        else dataFromServer
            .observe(viewLifecycleOwner, Observer { lisOfData ->
                lisOfData?.let {
                    title = it.title
                    dataList = it.rows
                    dataListAdapter?.swap(it.rows)
                    progressBar?.visibility = GONE
                    items_swipe_to_refresh.isRefreshing = false
                    dataListAdapter?.notifyDataSetChanged()

                }
            })
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            dataListAdapter = DataListAdapter(
                dataList,
                this@ListFragment
            )
            layoutManager = LinearLayoutManager(this@ListFragment.context)
            adapter = dataListAdapter
        }
    }

    private fun setupViewModel() {
        mainActivityViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(MainActivityViewModel::class.java)
    }

    override fun onItemSelected(position: Int, item: Row) {
        d("Item clicked : position : $position  and Item : $item")
        val navDirection = ListFragmentDirections.actionListFragmentToDetailFragment(item, title)
        findNavController().navigate(navDirection)
    }
}