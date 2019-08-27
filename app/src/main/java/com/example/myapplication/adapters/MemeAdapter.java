package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.models.MemeDto;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MemeAdapter extends RecyclerView.Adapter<MemeAdapter.MemeHolder> {

    private List<MemeDto> memes = new ArrayList<>();


    @NonNull
    @Override
    public MemeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_meme_block, parent, false);
        return new MemeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemeHolder holder, int position) {
        holder.bind(memes.get(position));
    }

    @Override
    public int getItemCount() {
        return memes.size();
    }

    public void setItems(Collection<MemeDto> memes) {
        this.memes.addAll(memes);
        notifyDataSetChanged();
    }

    public void clearItems() {
        memes.clear();
        notifyDataSetChanged();
    }

    public class MemeHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView image;

        public MemeHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleMeme);
            image=itemView.findViewById(R.id.imageMeme);
        }

        public void bind(MemeDto meme){
            title.setText(meme.getTitle());
            Picasso.get().load(meme.getPhotoUtl()).into(image);
        }
    }
}
