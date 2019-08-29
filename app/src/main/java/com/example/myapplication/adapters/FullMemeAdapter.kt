package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.MemeDto
import com.squareup.picasso.Picasso
import java.util.ArrayList

class FullMemeAdapter : RecyclerView.Adapter<FullMemeAdapter.MemeHolder>() {

    private val fullMemes = ArrayList<MemeDto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_meme_details, parent, false) //НЕ ВСЕ А ОДИН ПО ИД
        return MemeHolder(view)
    }

    override fun onBindViewHolder(holder: MemeHolder, position: Int) {
        holder.bind(fullMemes[position])
    }

    override fun getItemCount(): Int {
        return fullMemes.size
    }

    fun setItems(memes: Collection<MemeDto>) {
        this.fullMemes.addAll(memes)
        notifyDataSetChanged()
    }

    fun setItem(meme: MemeDto){
        this.fullMemes.add(meme)
        notifyDataSetChanged()
    }

    fun clearItems() {
        fullMemes.clear()
        notifyDataSetChanged()
    }

    inner class MemeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.titleFullMeme)
        private val image: ImageView = itemView.findViewById(R.id.imageFullMeme)
        private val date: TextView = itemView.findViewById(R.id.dateFullMeme)
        private val description: TextView = itemView.findViewById(R.id.descriptionFullMeme)

        fun bind(meme: MemeDto) {
            title.text = meme.title
            Picasso.get().load(meme.photoUtl).into(image)
            date.text = meme.createdDate
            description.text = meme.description
        }
    }

    inner class ClickListener : RecyclerView.OnItemTouchListener {

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }

    }
}
