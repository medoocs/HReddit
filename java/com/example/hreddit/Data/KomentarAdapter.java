package com.example.hreddit.Data;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hreddit.Model.Komentar;
import com.example.hreddit.Model.Komentar;
import com.example.hreddit.Model.User;
import com.example.hreddit.R;
import com.example.hreddit.forumScreen;

public class KomentarAdapter extends ListAdapter<Komentar, KomentarAdapter.KomentarHolder> {
    private OnItemClickListener listener;
    private OnLongClickListener listener2;
    private UserViewModel userViewModel;
    private User userProfile;

    public KomentarAdapter(UserViewModel userViewModel) {
        super(DIFF_CALLBACK);
        this.userViewModel = userViewModel;
    }

    private static final DiffUtil.ItemCallback<Komentar> DIFF_CALLBACK = new DiffUtil.ItemCallback<Komentar>() {
        @Override
        public boolean areItemsTheSame(Komentar oldItem, Komentar newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Komentar oldItem, Komentar newItem) {
            return oldItem.getTekst().equals(newItem.getTekst()) &&
                    oldItem.getUser().equals(newItem.getUser());
        }
    };

    @NonNull
    @Override
    public KomentarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View komentarView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_forum_screen, parent, false);
        return new KomentarHolder(komentarView);
    }

    @Override
    public void onBindViewHolder(@NonNull KomentarHolder holder, int position) {
        Komentar currentKomentar = getItem(position);

        holder.komentar.setText(currentKomentar.getTekst());
        holder.user.setText(currentKomentar.getUser());
        holder.datum.setText(currentKomentar.getDatum());
        holder.upvote.setText(Integer.toString(currentKomentar.getUpvote()));
        holder.downvote.setText(Integer.toString(currentKomentar.getDownvote()));

        userProfile = userViewModel.getUsername(currentKomentar.getUser());
        if(userProfile.getCommentScore() <= 99){
            holder.bronzeCOff.setVisibility(View.VISIBLE);
            holder.bronzeCOn.setVisibility(View.GONE);
            holder.silverCOn.setVisibility(View.GONE);
            holder.goldCOn.setVisibility(View.GONE);
        }else if(userProfile.getCommentScore() >= 100 && userProfile.getCommentScore() <= 999){
            holder.bronzeCOff.setVisibility(View.GONE);
            holder.bronzeCOn.setVisibility(View.VISIBLE);
            holder.silverCOn.setVisibility(View.GONE);
            holder.goldCOn.setVisibility(View.GONE);
        }else if(userProfile.getCommentScore() >= 1000 && userProfile.getCommentScore() <= 10000){
            holder.bronzeCOff.setVisibility(View.GONE);
            holder.bronzeCOn.setVisibility(View.GONE);
            holder.silverCOn.setVisibility(View.VISIBLE);
            holder.goldCOn.setVisibility(View.GONE);
        }else{
            holder.bronzeCOff.setVisibility(View.GONE);
            holder.bronzeCOn.setVisibility(View.GONE);
            holder.silverCOn.setVisibility(View.GONE);
            holder.goldCOn.setVisibility(View.VISIBLE);
        }
    }

    public Komentar getKomentarAt(int position) {
        return getItem(position);
    }

    class KomentarHolder extends RecyclerView.ViewHolder {
        private TextView komentar;
        private TextView user;
        private TextView datum;
        private TextView upvote;
        private TextView downvote;
        private ImageView imageUp;
        private ImageView imageDown;
        ImageView bronzeCOff, bronzeCOn, silverCOn, goldCOn;



        public KomentarHolder(View itemView) {
            super(itemView);
            komentar = itemView.findViewById(R.id.komentar);
            user = itemView.findViewById(R.id.userTxt);

            user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onUserClick(getItem(position));
                    }
                }
            });

            datum = itemView.findViewById(R.id.dateTxt);
            upvote = itemView.findViewById(R.id.upvoteTxt);
            downvote = itemView.findViewById(R.id.downvoteTxt);

            bronzeCOff = itemView.findViewById(R.id.bronzecommentoff);
            bronzeCOn = itemView.findViewById(R.id.bronzecommenton);
            silverCOn = itemView.findViewById(R.id.silvercommenton);
            goldCOn = itemView.findViewById(R.id.goldcommenton);

            imageUp = itemView.findViewById(R.id.imup);
            imageUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onUpClick(getItem(position));
                    }
                }
            });

            imageDown = itemView.findViewById(R.id.imdown);
            imageDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onDownClick(getItem(position));
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onKomentarClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onKomentarClick(Komentar komentar);
        void onUpClick(Komentar komentar);
        void onDownClick(Komentar komentar);
        void onUserClick(Komentar komentar);
    }
    public interface OnLongClickListener {
        void onLongTouch(Komentar komentar);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public void setOnLongClickListener(OnLongClickListener listener) {
        this.listener2 = listener;
    }
}
