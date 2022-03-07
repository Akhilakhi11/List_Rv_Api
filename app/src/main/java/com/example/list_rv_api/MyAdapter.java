package com.example.list_rv_api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    List<DataItem>data;
    Context context;

    private OnRecyclerViewClickListener listener;

    public interface OnRecyclerViewClickListener{
        void OnItemClick(DataItem position);
    }
    public void OnRecyclerViewClickListener (OnRecyclerViewClickListener listener){
        this.listener = (OnRecyclerViewClickListener) listener;
    }

    public MyAdapter(Context context, List<DataItem> data){
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.customdesign,parent,false);
        return new MyAdapter.ViewHolder(view,listener);
    }
    @SuppressLint("RecycleView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.fnametv.setText(data.get(position).getFirstName());
        holder.lnametv.setText(data.get(position).getLastName());
        holder.emailtv.setText(data.get(position).getEmail());

        String thumbnail= "https://reqres.in/img/faces/1-image.jpg";
        Glide.with(context)
                .load(data.get(position).getAvatar())
                .into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView fnametv;
        TextView lnametv;
        TextView emailtv;
        ImageView thumbnail;



        public ViewHolder(@NonNull View itemView,OnRecyclerViewClickListener listener) {
            super(itemView);
            fnametv = itemView.findViewById(R.id.fname);
            lnametv = itemView.findViewById(R.id.lname);
            emailtv = itemView.findViewById(R.id.mail);
            thumbnail = itemView.findViewById(R.id.thumbnail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!=null && getAbsoluteAdapterPosition()!=RecyclerView.NO_POSITION){
                        listener.OnItemClick(data.get(getAbsoluteAdapterPosition()));
                    }
                }
            });
        }
    }
}
