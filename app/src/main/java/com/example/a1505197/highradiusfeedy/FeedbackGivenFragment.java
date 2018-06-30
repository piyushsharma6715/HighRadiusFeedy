package com.example.a1505197.highradiusfeedy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1505197 on 6/29/2018.
 */

public class FeedbackGivenFragment extends Fragment
{
    DatabaseReference Feedbackgiven;
    ArrayList<Feedbacks> feedbackGiven;
    FeedBackGivenAdapter feedBackGivenAdapter;
    RecyclerView recyclerView;
    View view2;
    int positive=0,negative=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_feedback_given,container,false);
        recyclerView=view.findViewById(R.id.userSideRecyclerView);
        feedbackGiven=new ArrayList<>();
        view2=view;

        Feedbackgiven= FirebaseDatabase.getInstance().getReference("feedbackgiven");
        Feedbackgiven.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                    {
                        Feedbacks feedbacks=dataSnapshot1.getValue(Feedbacks.class);
                        feedbackGiven.add(feedbacks);
                    }
                }

                feedBackGivenAdapter=new FeedBackGivenAdapter(getActivity(),feedbackGiven);
                recyclerView.setAdapter(feedBackGivenAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                feedBackGivenAdapter.notifyDataSetChanged();
                List<PieEntry> p=new ArrayList<>();
                TellFeedbackCount tellFeedbackCount=new TellFeedbackCount();
                positive=tellFeedbackCount.getPositive();
                negative=tellFeedbackCount.getNegative();
                p.add(new PieEntry(positive,"Positive"));
                p.add(new PieEntry(negative,"Negative"));


                PieDataSet pieDataSet=new PieDataSet(p,"Feedback");
                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                PieData pieData=new PieData(pieDataSet);
                PieChart chart=view2.findViewById(R.id.ratingChart);
                chart.setCenterText("Feedback");
                chart.setData(pieData);
                chart.animateY(1000);
                chart.invalidate();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
