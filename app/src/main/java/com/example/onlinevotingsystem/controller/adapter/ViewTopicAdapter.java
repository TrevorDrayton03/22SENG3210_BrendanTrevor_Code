package com.example.onlinevotingsystem.controller.adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinevotingsystem.model.Topic;
import com.example.onlinevotingsystem.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ViewTopicAdapter extends RecyclerView.Adapter<ViewTopicAdapter.ViewHolder> {

    private Topic dataModalTopic = new Topic();

    public ViewTopicAdapter(Topic dataModalTopic) {
        this.dataModalTopic = dataModalTopic;
    }

    @Override
    public ViewTopicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        return new ViewTopicAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_dynamic_option_buttons, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewTopicAdapter.ViewHolder holder, int position) {
        // get the data model based on position
        Map<String, Integer> options = dataModalTopic.getOptions();
        // extract the keys into a set
        Set<String> keySet = dataModalTopic.getOptions().keySet();
        // convert set of keys to ArrayList
        ArrayList<String> listOfKeys = new ArrayList<>(keySet);
        // convert a collection of values
        Collection<Integer> values = options.values();
        // convert the collection to List so that we can get values by position
        List listOfValues = new ArrayList(values);

        Switch button = holder.dynamicOptionButton;
        button.setText(listOfKeys.get(position));
        button.setTag(listOfValues.get(position));
        Log.d("OptionAdapter", "Button tag value is: " + button.getTag());
    }

    @Override
    public int getItemCount() {
        // returning the size of options
        return dataModalTopic.getOptions().size();
    }
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        public Switch dynamicOptionButton;
        // Create a constructor that accepts the entire item column
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            super(itemView);
            dynamicOptionButton = itemView.findViewById(R.id.dynamicOptionButton);
        }
    }
}
