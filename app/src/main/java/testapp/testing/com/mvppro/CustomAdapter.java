package testapp.testing.com.mvppro;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import testapp.testing.com.mvppro.addy.Car;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    ArrayList<Car> personNames;
    Context context;
    public CustomAdapter(Context context, ArrayList personNames) {
        this.context = context;
        this.personNames = personNames;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // set the data in items
        holder.name.setText(personNames.get(position).getNameOfCar());
        Picasso.with(context).load(personNames.get(position).getImage()).into(holder.ivImage);
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Toast.makeText(context, personNames.get(position).getNameOfCar(), Toast.LENGTH_SHORT).show();
            }
        });
    }


     @Override
    public int getItemCount() {
        return personNames.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;// init the item view's
        ImageView ivImage;
        public ViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.name);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
        }
    }
}