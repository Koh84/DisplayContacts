package com.kelvin.contactapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    int images[];
    private Context mContext;
    private List<MyContacts> mListContacts;

    public MyAdapter(Context context, List<MyContacts> listContacts) {
        mContext = context;
        mListContacts = listContacts;
        //images = img;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        TextView contact_name, contact_number;

        contact_name =  holder.contact_name;
        contact_number = holder.contact_number;

        contact_name.setText(mListContacts.get(position).getName());
        contact_number.setText(mListContacts.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return mListContacts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView contact_name, contact_number;
        ImageView myImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            contact_name = itemView.findViewById(R.id.myTitle);
            contact_number = itemView.findViewById(R.id.myDescription);
            myImage = itemView.findViewById(R.id.myImage);
        }
    }
}
