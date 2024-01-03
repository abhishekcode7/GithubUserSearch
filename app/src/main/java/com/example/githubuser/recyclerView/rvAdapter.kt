package com.example.githubuser.recyclerView

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.MainActivity
import com.example.githubuser.R
import com.example.githubuser.dataClasses.User

class rvAdapter(private val mList: List<User>) : RecyclerView.Adapter<rvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user = mList[position]

        Glide.with(holder.imageView.context).load(user.avatarUrl).into(holder.imageView)
        holder.textView.text = user.username
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,MainActivity::class.java)
            intent.putExtra(MainActivity.SEARCH_USER,user.username)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = this.itemView.findViewById(R.id.user_image)
        val textView: TextView = this.itemView.findViewById(R.id.username)
    }
}