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


public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.ViewHolder> {

    // array list variable for storing topics, which we'll use to get the options
    private ArrayList<Topic> dataModalArrayList = new ArrayList<>();

    // pass in array list variable into constructor
    public OptionAdapter(ArrayList<Topic> dataModalArrayList) {
        this.dataModalArrayList = dataModalArrayList;
    }

    @Override
    public OptionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        return new OptionAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_dynamic_option_buttons, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OptionAdapter.ViewHolder holder, int position) {
        // get the data model based on position
        Topic topic = dataModalArrayList.get(position);

        // set item views
        Button button = holder.dynamicOptionButton;
        //TODO: naming each option
        button.setText(topic.getTitle());
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return dataModalArrayList.size();
    }
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public Button dynamicOptionButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            dynamicOptionButton = (Button) itemView.findViewById(R.id.dynamicOptionButton);
        }
    }
}
