package com.example.medicareapp;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import androidx.recyclerview.widget.RecyclerView;




public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<LogModel> logList;
    private onItemClickListener mListener;

    public interface onItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener)
    {
        mListener=listener;
    }
    //getting the context and product list with constructor
    public LogAdapter(Context mCtx, List<LogModel> logList) {
        this.mCtx = mCtx;
        this.logList = logList;
    }

    @Override
    public LogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_log, null);
        return new LogViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(LogViewHolder holder, int position) {
        //getting the product of the specified position
        LogModel logModel = logList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(logModel.getLog_name());
        //holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));
        Log.e("image",logModel.getImage_name());
        int i=mCtx.getResources().getIdentifier(logModel.getImage_name(),"drawable",mCtx.getPackageName());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(i));

    }


    @Override
    public int getItemCount() {
        return logList.size();
    }


    class LogViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        ImageView imageView;

        public LogViewHolder(View itemView, final onItemClickListener listener) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.text_log);
            imageView = itemView.findViewById(R.id.image_log);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}