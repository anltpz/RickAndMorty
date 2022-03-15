package com.example.bindingAdapter


import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.rickandmorty.R


class BindingAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(thubmImage: ImageView, url: String) {
            Glide.with(thubmImage)
                .load(url)
                .into(thubmImage);
        }

        @JvmStatic
        @BindingAdapter("drawableSet")
        fun ImageView.setDrawable(status: String)
        {
            when(status)
            {
                "Alive"->this.setImageResource(R.drawable.ic_circle_green)
                "Dead"->this.setImageResource(R.drawable.ic_circle_red)
                "unknown"->this.setImageResource(R.drawable.ic_gray_circle)
                else ->this.setImageResource(R.drawable.ic_circle_red)
            }
        }

        @JvmStatic
        @BindingAdapter("isim5karakterdenAz")
        fun TextView.nameTest(text:String)
        {
            if (text.length>5)
            {
                this.text="5 karakterden fazla"
            }
            else {

            }
        }





    }


}