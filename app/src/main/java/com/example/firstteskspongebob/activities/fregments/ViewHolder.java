package com.example.firstteskspongebob.activities.fregments;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstteskspongebob.R;
import com.example.firstteskspongebob.interfaces.OnItemClickListener;
import com.example.firstteskspongebob.logic.Player;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView activity_player_info_IMG_imageview;
    private TextView activity_player_info_TXT_name;
    private TextView activity_player_info_TXT_score;
    private LinearLayoutCompat linearLayoutCompat_info;
    private Player player;
    private OnItemClickListener mListener;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        findViews();
    }

    public void setmListener (OnItemClickListener mListener){
        this.mListener = mListener;
    }
    private void findViews() {
        activity_player_info_IMG_imageview = itemView.findViewById(R.id.activity_player_info_IMG_imageview);
        activity_player_info_TXT_name = itemView.findViewById(R.id.activity_player_info_TXT_name);
        activity_player_info_TXT_score = itemView.findViewById(R.id.activity_player_info_TXT_score);
        linearLayoutCompat_info = itemView.findViewById(R.id.linearLayoutCompat_info);

    }
    public ImageView getActivity_player_info_IMG_imageview() {return activity_player_info_IMG_imageview;}
    public TextView getActivity_player_info_name() {
        return activity_player_info_TXT_name;
    }
    public TextView getActivity_player_info_score() {
        return activity_player_info_TXT_score;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void onClick(View v) {
        mListener.onItemClick(player);
    }
}