package com.example.foodapplication.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.foodapplication.R
import com.example.foodapplication.adapter.DashboardRecyclerAdapter
import com.example.foodapplication.model.Food
import com.example.foodapplication.util.connectionManager


class HomeFragment : Fragment() {

    lateinit var recyclerDashboard : RecyclerView
    lateinit var layoutManager : RecyclerView.LayoutManager

    lateinit var recyclerAdapter: DashboardRecyclerAdapter

    lateinit var conntbtn : Button

     val foodListInfo = arrayListOf<Food>(
         Food("Baskin-Robbins","₹ 109/person","4.3",R.drawable.baskin_robbins),
         Food("Burger King","₹ 149/person","3.9",R.drawable.burgerking),
         Food("Burger Singh","₹ 129/person","4.2",R.drawable.burgersingh),
         Food("Cracker-Barrel","₹ 239/person","4.0",R.drawable.cracker_barrel),
         Food("Domino's","₹ 199/person","3.5",R.drawable.dominos),
         Food("DQ","₹ 159/person","3.7",R.drawable.dq),
         Food("KFC","₹ 299/person","3.9",R.drawable.kfc),
         Food("McDonald's","₹ 129/person","4.2",R.drawable.mcdonalds),
         Food("Olive-Garden","₹ 229/person","4.2",R.drawable.olive_garden),
         Food("Pizza Hut","₹ 129/person","4.2",R.drawable.pizzahut),
         Food("Popeye's","₹ 159/person","4.2",R.drawable.popeyes),
         Food("Red-Lobster","₹ 349/person","4.2",R.drawable.red_lobster),
         Food("Subway","₹ 129/person","4.2",R.drawable.subway),
         Food("Taco-Bell","₹ 149/person","4.2",R.drawable.taco_bell1),
         Food("Wendy's","₹ 199/person","4.2",R.drawable.wendys)


    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        recyclerDashboard = view.findViewById(R.id.recyclerDashboard)

        conntbtn = view.findViewById(R.id.conntbtn)

        conntbtn.setOnClickListener{
            if (connectionManager().checkConnectivity(activity as Context)){
                val dialog = AlertDialog.Builder(activity as Context)
                dialog.setTitle("Success")
                dialog.setMessage("Internet connection found")
                dialog.setPositiveButton("Ok"){text, listener ->
                }
                dialog.setNegativeButton("Cancel"){text, listener ->
                }
                dialog.create()
                dialog.show()
            }
            else{
                val dialog = AlertDialog.Builder(activity as Context)
                dialog.setTitle("Error")
                dialog.setMessage("Internet connection not found")
                dialog.setPositiveButton("Ok"){text, listener ->
                }
                dialog.setNegativeButton("Cancel"){text, listener ->
                }
                dialog.create()
                dialog.show()
            }
        }

        layoutManager = LinearLayoutManager(activity)

        recyclerAdapter = DashboardRecyclerAdapter(activity as Context, foodListInfo)

        recyclerDashboard.adapter = recyclerAdapter

        recyclerDashboard.layoutManager = layoutManager

        recyclerDashboard.addItemDecoration(
            DividerItemDecoration(
                recyclerDashboard.context,
                (layoutManager as LinearLayoutManager).orientation
            )
        )

        val queue = Volley.newRequestQueue(activity as Context)

        val url = "http://13.235.250.119/v2/restaurants/fetch_result/"

        val jsonObjectRequest = object : JsonObjectRequest(Method.GET,url, null,
            Response.Listener {

        } , Response.ErrorListener {

        }){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-type"] = "application/json"
                headers["token"] = "dd00eae47b660d"
                return headers
            }
        }

        return view

    }


}