package com.example.khadijeh_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.khadijeh_project.model.Post;

import java.util.ArrayList;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.ViewHolder> {

    private ArrayList<Post> list;
    private Context context;

    public AdapterSearch(Context context, ArrayList<Post> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public AdapterSearch.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_list_layout,parent , false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( AdapterSearch.ViewHolder holder, int position) {
        Post model = list.get(position);
        holder.tv_title.setText(model.title);
        holder.tv_dis.setText(model.excerpt);
        Glide.with(context).load(model.file.thumb).placeholder(R.drawable.load2).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ;
        TextView tv_title;
        TextView tv_dis;

        public ViewHolder( View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.coverimage);
            tv_title = itemView.findViewById(R.id.title);
            tv_dis = itemView.findViewById(R.id.ditails);


        }
    }
}
