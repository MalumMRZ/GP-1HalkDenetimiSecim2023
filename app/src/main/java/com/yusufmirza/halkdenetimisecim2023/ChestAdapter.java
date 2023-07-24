package com.yusufmirza.halkdenetimisecim2023;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class ChestAdapter extends RecyclerView.Adapter<ChestAdapter.ChestHolder> {

    ArrayList<Chest> chestArrayList;

    public ChestAdapter(ArrayList<Chest> chestArrayList){
        this.chestArrayList=chestArrayList;
    }



    @NonNull
    @Override
    public ChestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.chest_cell,parent,false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight()/5);

        return new ChestHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ChestHolder holder, @SuppressLint("RecyclerView") int position) {

          holder.chestNumber.setText(chestArrayList.get(position).chestNumber);
          holder.kemalVote.setText("KK : "+ chestArrayList.get(position).kemal);
          holder.tayyipVote.setText("RTE : "+ chestArrayList.get(position).tayyip);

          int kemal= chestArrayList.get(position).kemal;
          int tayyip = chestArrayList.get(position).tayyip;
          if(kemal>tayyip){
              holder.itemView.setBackgroundColor(Color.RED);
          } else if (tayyip>kemal){
              holder.itemView.setBackgroundColor(Color.YELLOW);
          } else {
              holder.itemView.setBackgroundColor(Color.GREEN);
          }

          holder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(holder.itemView.getContext(),ChestActivity.class);
                  intent.putExtra("chest", (Serializable) chestArrayList.get(position));
                  intent.putExtra("position",position);
                  holder.itemView.getContext().startActivity(intent);
              }
          });


    }



    @Override
    public int getItemCount() {
        return chestArrayList.size();
    }


    public class ChestHolder extends RecyclerView.ViewHolder{

        TextView chestNumber,kemalVote,tayyipVote;

        public ChestHolder(@NonNull View itemView) {
            super(itemView);
            chestNumber= itemView.findViewById(R.id.chestId);
            kemalVote = itemView.findViewById(R.id.kemalView);
            tayyipVote = itemView.findViewById(R.id.recepView);


        }
    }

}
