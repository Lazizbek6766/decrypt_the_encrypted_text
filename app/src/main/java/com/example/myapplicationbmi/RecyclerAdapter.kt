package com.example.myapplicationbmi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationbmi.databinding.RecyclerBinding

class RecyclerAdapter (
    val context: Context,
    val arrayList: ArrayList<Modul>
    ):RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = RecyclerBinding.inflate(LayoutInflater.from(context),parent,false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.binding.apply {
            textView.text = arrayList[position].shmatn
            textView2.text = arrayList[position].chastata
            textView3.text = arrayList[position].k
            textView4.text = arrayList[position].matn

            btn.setOnClickListener {
                val intent = Intent(context,MainActivity2::class.java)
                intent.putExtra("shmatn",arrayList[position].shmatn)
                intent.putExtra("chastata",arrayList[position].chastata)
                intent.putExtra("k",arrayList[position].k)
                intent.putExtra("matn",arrayList[position].matn)
                context.startActivity(intent)
            }
        }
    }
    class RecyclerViewHolder(val binding: RecyclerBinding):RecyclerView.ViewHolder(binding.root)
}