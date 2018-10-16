package myproject.com.myrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<String> imageurl = new ArrayList<>();
    private Context mContext;

    //default constructor will get names of images and images and context for us
    public RecyclerViewAdapter(ArrayList<String> mImageNames, ArrayList<String> mImages, Context mContext) {
        this.title = mImageNames;
        this.imageurl = mImages;
        this.mContext = mContext;
    }

    //this method is the one responsible for inflaming the views
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Picasso.get().load(imageurl.get(position)).into(holder.imageView);
        holder.textView.setText(title.get(position));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "onClick: " + position + " " + title.get(position), Toast.LENGTH_SHORT ).show();
//
//                Intent intent = new Intent(mContext , activity_gallery.class);
//                intent.putExtra("image_url", imageurl.get(position));
//                intent.putExtra("image_name", title.get(position));
//
//                mContext.startActivity(intent);
            }
        });


    }

    //tells the adapter numbers of list items in your list
    @Override
    public int getItemCount() {
        return title.size();
    }

    //holds the widgets, individually , its holding that view and getting ready to add next one
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);

        }
    }
}
