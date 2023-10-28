package com.example.weatherapp.adapters

import android.graphics.Color
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ListItemBinding
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class WeatherAdapter(val listener:Listener?) : ListAdapter<WeatherModel, WeatherAdapter.Holder>(Comparator()){

    class Holder(view: View,val listener: Listener?): RecyclerView.ViewHolder(view){
        val binding = ListItemBinding.bind(view)

        var itemTemp: WeatherModel? = null
        init {
            itemView.setOnClickListener{
                itemTemp?.let{ it1 ->
                    listener?.onClick(it1)
                }
            }
        }
        fun bind(item: WeatherModel) = with(binding){

            itemTemp = item
            tvDate.text = item.time.takeLast(5)

            if (item.condition.equals("patchy rain possible", true)) {
                tvConditions.text = "Patchy rain\npossible"
            }else tvConditions.text = item.condition

            if(item.currentTemp.isEmpty()){
                vSeparator.setVisibility(View.VISIBLE)
                tvMax.text =  item.maxTemp.toDouble().roundToInt()
                    .toString().plus("°C")
                tvMin.text =  item.minTemp.toDouble().roundToInt()
                    .toString().plus("°C")
            } else {
                tvTemp.setVisibility(View.VISIBLE);
                tvTemp.setTextColor(Color.parseColor("#FFFFFF"));
                tvTemp.text = item.currentTemp
            }
            Picasso.get().load("https:${item.imageUrl}").into(imListIcon)
        }
    }

    class  Comparator : DiffUtil.ItemCallback<WeatherModel>(){
        override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return Holder(view, listener)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    interface Listener{
        fun onClick(Item:WeatherModel)
    }
}
