package com.example.hreddit.Data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hreddit.Model.Forum;
import com.example.hreddit.R;

public class ForumAdapter extends ListAdapter<Forum, ForumAdapter.ForumHolder> {
    private OnItemClickListener listener;
    private OnLongClickListener listener2;

    public ForumAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Forum> DIFF_CALLBACK = new DiffUtil.ItemCallback<Forum>() {
        @Override
        public boolean areItemsTheSame(Forum oldItem, Forum newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Forum oldItem, Forum newItem) {
            return oldItem.getNaslov().equals(newItem.getNaslov()) &&
                    oldItem.getTekst().equals(newItem.getTekst()) &&
                    oldItem.getKategorija().equals(newItem.getKategorija());
        }
    };

    @NonNull
    @Override
    public ForumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View forumView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_home_screen, parent, false);
        return new ForumHolder(forumView);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumHolder holder, int position) {
        Forum currentForum = getItem(position);

        holder.naslov.setText(currentForum.getNaslov());
        holder.upvote.setText(Integer.toString(currentForum.getUpvote()));
        holder.downvote.setText(Integer.toString(currentForum.getDownvote()));
        holder.brojComm.setText(Integer.toString(currentForum.getBrojKomentara()));
        if(currentForum.getKategorija().equals("Food")) holder.category.setImageResource(R.drawable.ic_foodon);
        else if(currentForum.getKategorija().equals("Book")) holder.category.setImageResource(R.drawable.ic_bookon);
        else if(currentForum.getKategorija().equals("Games")) holder.category.setImageResource(R.drawable.ic_gameson);
        else if(currentForum.getKategorija().equals("Movies")) holder.category.setImageResource(R.drawable.ic_movieson);
        else if(currentForum.getKategorija().equals("Music")) holder.category.setImageResource(R.drawable.ic_musicon);
        else if(currentForum.getKategorija().equals("Nature")) holder.category.setImageResource(R.drawable.ic_natureon);
        else if(currentForum.getKategorija().equals("Places")) holder.category.setImageResource(R.drawable.ic_placeson);
        else if(currentForum.getKategorija().equals("Technology")) holder.category.setImageResource(R.drawable.ic_techon);
    }

    public Forum getForumAt(int position) {
        return getItem(position);
    }

    class ForumHolder extends RecyclerView.ViewHolder {
        private TextView naslov;
        private TextView upvote;
        private TextView downvote;
        private TextView brojComm;
        private ImageView category;


        public ForumHolder(View itemView) {
            super(itemView);
            naslov = itemView.findViewById(R.id.naslovForuma);
            upvote = itemView.findViewById(R.id.upBroj);
            downvote = itemView.findViewById(R.id.downBroj);
            brojComm = itemView.findViewById(R.id.commBroj);
            category = (ImageView) itemView.findViewById(R.id.imCategory);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();
                    if (listener2 != null && position != RecyclerView.NO_POSITION) {
                        listener2.onLongTouch(getItem(position));
                    }
                    return false;
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Forum forum);
    }
    public interface OnLongClickListener {
        void onLongTouch(Forum forum);
    }
    public void setOnLongClickListener(OnLongClickListener listener) {
        this.listener2 = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
