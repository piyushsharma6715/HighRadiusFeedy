package com.example.a1505197.highradiusfeedy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 1505197 on 7/5/2018.
 */

public class FragmentFoodAdapter extends RecyclerView.Adapter<FragmentFoodAdapter.VendorViewHolder>
{
    Context context;
    LayoutInflater inflater;
    List<FoodObject> data;
    public FragmentFoodAdapter(Context context, List<FoodObject> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public FragmentFoodAdapter.VendorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.food_security_view_cards, parent, false);
        FragmentFoodAdapter.VendorViewHolder holder = new FragmentFoodAdapter.VendorViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FragmentFoodAdapter.VendorViewHolder holder, int position) {


        holder.name.setText(data.get(position).name);
        holder.department.setText("Food");
        holder.happysmiley.setText(data.get(position).positive.toString());
        holder.sadsmiley.setText(data.get(position).negative.toString());


        Picasso.with(context)
                .load(data.get(position).image_url)
                .fit()
                .centerCrop()
                .into(holder.circleImageView);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class VendorViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView department;
        TextView happysmiley;
        TextView neutralsmiley;
        TextView sadsmiley;
        ImageView circleImageView;

        public VendorViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.review_card_name);
            department=itemView.findViewById(R.id.review_card_department);
            happysmiley=itemView.findViewById(R.id.positive_count_food);
            //neutralsmiley=itemView.findViewById(R.id.numberNeutral);
            sadsmiley=itemView.findViewById(R.id.negative_count_food);
            circleImageView=itemView.findViewById(R.id.review_card_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent intent=new Intent(context,FoodFeedbackActivity.class);
                    intent.putExtra("question",data.get(getAdapterPosition()).getQuestion());
                    context.startActivity(intent);
                }
            });


        }
    }
}
