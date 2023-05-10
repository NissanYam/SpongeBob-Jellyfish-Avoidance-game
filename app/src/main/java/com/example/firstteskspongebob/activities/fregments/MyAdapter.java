package com.example.firstteskspongebob.activities.fregments;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.firstteskspongebob.R;
import com.example.firstteskspongebob.interfaces.OnItemClickListener;
import com.example.firstteskspongebob.logic.Player;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context context;
    private ArrayList<Player> players;
    private OnItemClickListener mListener;


    public MyAdapter(Context context, ArrayList<Player> players, OnItemClickListener listener) {
        this.context = context;
        this.players = players;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_player_info,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Player player = players.get(position);
        holder.setPlayer(player);
        holder.getActivity_player_info_name().setText(player.getName());
        holder.getActivity_player_info_score().setText(""+player.getScore());
        holder.getActivity_player_info_IMG_imageview().setImageResource(player.getImage());
        holder.setmListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Player player) {
                int pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(players.get(pos));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

}
