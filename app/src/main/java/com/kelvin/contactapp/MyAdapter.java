package com.kelvin.contactapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
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
        ImageView myImage;

        contact_name =  holder.contact_name;
        contact_number = holder.contact_number;
        myImage = holder.myImage;

        contact_name.setText(mListContacts.get(position).getName());
        contact_number.setText(mListContacts.get(position).getNumber());

        try
        {
            String image_uri = mListContacts.get(position).getImage();
            if(image_uri!=null)
            {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), Uri.parse(image_uri));
                myImage.setImageBitmap(bitmap);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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
