package com.kelvin.contactapp;

import android.content.Context;
import android.graphics.Bitmap;
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
    private OnContactListener mOnContactListener;

    public MyAdapter(Context context, List<MyContacts> listContacts, OnContactListener mOnContactListener) {
        mContext = context;
        mListContacts = listContacts;
        this.mOnContactListener = mOnContactListener;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view, mOnContactListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        TextView contact_name, contact_number, contact_id;
        ImageView myImage;

        contact_name =  holder.contact_name;
        contact_number = holder.contact_number;
        myImage = holder.myImage;
        contact_id = holder.contact_id;

        contact_name.setText(mListContacts.get(position).getName());
        contact_number.setText(mListContacts.get(position).getNumber());
        contact_id.setText(mListContacts.get(position).getId());

        //System.out.println("Contact id: "+contact_id );

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

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView contact_name, contact_number, contact_id;
        ImageView myImage;

        OnContactListener onContactListener;

        public MyViewHolder(@NonNull View itemView, OnContactListener onContactListener) {
            super(itemView);

            contact_name = itemView.findViewById(R.id.myTitle);
            contact_number = itemView.findViewById(R.id.myDescription);
            myImage = itemView.findViewById(R.id.myImage);
            contact_id = itemView.findViewById(R.id.myID);

            this.onContactListener = onContactListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            //onContactListener.onContactClick(getAdapterPosition());
            onContactListener.onContactClick(contact_id.getText().toString());
        }
    }

    public interface OnContactListener
    {
        void onContactClick(String contact_id);
    }
}
