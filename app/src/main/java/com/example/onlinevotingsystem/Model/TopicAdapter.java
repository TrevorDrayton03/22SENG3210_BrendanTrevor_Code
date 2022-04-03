package com.example.onlinevotingsystem.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinevotingsystem.R;

import java.util.ArrayList;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {

    // variable for storing topics
    private ArrayList<Topic> dataModalArrayList = new ArrayList<>();

    // pass in variable for storing topics into constructor
    public TopicAdapter(ArrayList<Topic> dataModalArrayList) {
        this.dataModalArrayList = dataModalArrayList;
    }

    @Override
    public TopicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        return new TopicAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.dynamic_topic_buttons, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TopicAdapter.ViewHolder holder, int position) {
        // get the data model based on position
        Topic topic = dataModalArrayList.get(position);

        // set item views
        Button button = holder.dynamicButton;
        button.setText(topic.getTitle());
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return dataModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our
        // views of recycler items.
        private Button dynamicButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing the views of recycler views.
            dynamicButton = itemView.findViewById(R.id.dynamicButton);
        }
    }
}