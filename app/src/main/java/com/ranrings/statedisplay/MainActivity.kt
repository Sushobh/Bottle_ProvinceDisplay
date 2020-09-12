package com.ranrings.statedisplay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ranrings.statedisplay.models.Province
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named


class MainActivity : AppCompatActivity() {

    val countryCode = "USA"
    val provinceList : ArrayList<Province> = arrayListOf()
    val adapter = ProvinceListAdapter(provinceList)

    val mainViewModel : MainViewModel by viewModel(clazz = MainViewModel::class) { parametersOf(countryCode) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel.onCreate()
        recycler_view.layoutManager = (LinearLayoutManager(this))
        recycler_view.adapter = adapter

        mainViewModel.provinceListLiveData.observe(this , Observer {
             if(it != null){
                 provinceList.clear()
                 provinceList.addAll(it)
                 adapter.notifyDataSetChanged()
             }
         })

        mainViewModel.progressBarDisplayLiveData.observe(this, Observer {
            if(it != null) {
                if(it){
                    progress_bar.visibility = View.VISIBLE
                }
                else {
                    progress_bar.visibility = View.INVISIBLE
                }
            }
        })

    }


    companion object {
        class ProvinceListAdapter(val provinceList : List<Province>) : RecyclerView.Adapter<ProvinceListAdapter.MyViewHolder>() {

            class MyViewHolder(var textView : TextView) : RecyclerView.ViewHolder(textView)
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                return MyViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.row_text_view,parent,false) as TextView
                )
            }

            override fun getItemCount(): Int {
                return provinceList.size
            }

            override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                holder.textView.text  = provinceList[position].name
            }


        }
    }


}