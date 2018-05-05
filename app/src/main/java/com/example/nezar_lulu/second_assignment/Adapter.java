package com.example.nezar_lulu.second_assignment;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nezar_lulu on 2017-10-23.
 */


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView name, number;


        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            number = (TextView) itemView.findViewById(R.id.number);

        }
    }

    private Context context;
    private List<list> posts;


    public Adapter(Context c, List<list> postList) {
        this.context = c;
        posts = postList;
    }


    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.list, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {

        list p = posts.get(position);
        holder.name.setText(p.getName());
        holder.number.setText(p.getNumber());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}