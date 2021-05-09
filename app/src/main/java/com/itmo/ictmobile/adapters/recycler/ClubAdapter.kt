package com.itmo.ictmobile.adapters.recycler

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itmo.ictmobile.R
import com.itmo.ictmobile.data.models.Club

class ClubAdapter : RecyclerView.Adapter<ClubAdapter.ClubViewHolder>() {

    private val clubs: MutableList<Club> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_club, parent, false)
        return ClubViewHolder(itemView)
    }

    override fun getItemCount() = clubs.size

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        holder.bind(clubs[position])
    }

    fun addClubs(newClubs: List<Club>) {
        clubs += newClubs
        notifyDataSetChanged()
    }

    fun clearClubs() {
        clubs.clear()
        notifyDataSetChanged()
    }

    class ClubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.title)
        private val icon: ImageView = itemView.findViewById(R.id.icon)

        fun bind(club: Club) {
            title.text = club.title
            icon.setImageResource(club.imageRes)

            itemView.setOnClickListener {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(club.url)

                itemView.context.startActivity(i)
            }
        }

    }

}