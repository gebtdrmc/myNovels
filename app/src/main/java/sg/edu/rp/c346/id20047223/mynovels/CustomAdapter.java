package sg.edu.rp.c346.id20047223.mynovels;

import android.content.Context;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Novel> novelList;

    public CustomAdapter(Context context, int resource, ArrayList<Novel> objects){
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        novelList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvName = rowView.findViewById(R.id.textViewName);
        TextView tvAuthor = rowView.findViewById(R.id.textViewAuthor);
        TextView tvGenre = rowView.findViewById(R.id.textViewGenre);
        TextView tvSynopsis = rowView.findViewById(R.id.textViewNote);
        RatingBar rbRating = rowView.findViewById(R.id.rbStars);
        TextView tvStatus = rowView.findViewById(R.id.textViewStatus);


        Novel currentItem = novelList.get(position);
        tvName.setText(currentItem.getTitle());
        tvAuthor.setText(currentItem.getAuthor());
        tvGenre.setText(currentItem.getGenre());
        tvSynopsis.setText(currentItem.getSynopsis());
        rbRating.setRating(currentItem.getRating());
        tvStatus.setText(currentItem.getStatus());



        return  rowView;
    }
}
