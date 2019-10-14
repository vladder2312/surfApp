package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.MemeDto
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Date
import java.util.*

class MemeAdapter : RecyclerView.Adapter<MemeAdapter.MemeHolder>() {

    private val memes = ArrayList<MemeDto>()
    private val mListeners: MutableList<ChangeListener> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            com.example.myapplication.R.layout.fragment_meme_block,
            parent,
            false
        )
        return MemeHolder(view).listen { position, _ ->
            changeSource(position)
        }
    }

    override fun onBindViewHolder(holder: MemeHolder, position: Int) {
        val item = memes[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return memes.size
    }

    fun setItems(memes: Collection<MemeDto>) {
        this.memes.addAll(memes)
        notifyDataSetChanged()
    }

    fun clearItems() {
        memes.clear()
        notifyDataSetChanged()
    }

    inner class MemeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titlePrev: TextView = itemView.findViewById(R.id.titleMeme)
        private val imagePrev: ImageView = itemView.findViewById(R.id.imageMeme)

        fun bind(meme: MemeDto) {
            titlePrev.text = meme.title
            Picasso.get().load(meme.photoUtl).into(imagePrev)
        }
    }

    fun addListener(listener: ChangeListener){
        mListeners.add(listener)
    }

    fun changeSource(position: Int){
        mListeners.forEach{
            it.sourceChanged(position)
        }
    }

    fun <T: RecyclerView.ViewHolder> T.listen(event: (position: Int,type:Int) -> Unit):T{
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }

    operator fun get(position: Int):MemeDto{
        return memes[position]
    }

    fun getFormattedDate(rawDate: String) : String {
        return SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date((rawDate.toLong())*1000))
    }
}
