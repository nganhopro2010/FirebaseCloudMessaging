package com.shapeecloudjsc.firebasecloudmessaging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class TourAdapter(private val context: Context, private val arrayList: ArrayList<TourList>) :
    BaseAdapter() {
    class ViewHolder(view: View) {
        val tvId: TextView = view.findViewById(R.id.tvId)
        val tvFromCity: TextView = view.findViewById(R.id.tvFromCity)
        val tvToCity: TextView = view.findViewById(R.id.tvToCity)
        val tvTourName: TextView = view.findViewById(R.id.tvTourName)
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, convertView: View?, p2: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val layoutInflater: LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.item_tour_list, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }
        val currentItem: TourList = arrayList[p0]
        viewHolder.tvId.text = currentItem.id.toString()
        viewHolder.tvFromCity.text = currentItem.fromCity
        viewHolder.tvToCity.text = currentItem.toCity
        viewHolder.tvTourName.text = currentItem.tourName

        return view as View

    }

}