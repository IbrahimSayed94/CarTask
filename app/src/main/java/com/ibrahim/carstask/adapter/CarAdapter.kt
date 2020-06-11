package com.ibrahim.carstask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ibrahim.carstask.click.CarClick
import com.ibrahim.carstask.R
import com.ibrahim.carstask.data.model.CarModel
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_car.view.*

class CarAdapter(
    private val context: Context,
    private var carList: List<CarModel>,
    private val carClick: CarClick
) :
    RecyclerView.Adapter<CarAdapter.View_Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): View_Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return View_Holder(v)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(holder: View_Holder, position: Int) {

        val car = carList.get(position)

        Glide.with(context)
            .applyDefaultRequestOptions(RequestOptions().placeholder(R.color.colorAccent))
            .load(car.Images[0])
            .into(holder.carImage)

        holder.carPrice.text = car.Price.toString()
        holder.carMake.text = car.Make
        holder.carMileAge.text = car.Mileage.toString()
        holder.carFuelType.text = car.FuelType

        holder.itemView.setOnClickListener {
            carClick.onClick(position)
        }

        animate(holder)

    }


    inner class View_Holder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        internal var carImage: CircleImageView = itemView.img_car
        internal var carPrice: TextView = itemView.txt_price
        internal var carMileAge: TextView = itemView.txt_mileage
        internal var carMake: TextView = itemView.txt_make
        internal var carFuelType: TextView = itemView.txt_fuel_type

    }

    fun setCarList(carList: List<CarModel>) {
        this.carList = carList
    } // fun of setCarList

    private fun animate(viewHolder: RecyclerView.ViewHolder) {
        val animAnticipateOvershoot = AnimationUtils.loadAnimation(context,
            R.anim.slide_in_right
        )
        viewHolder.itemView.animation = animAnticipateOvershoot
    }
}