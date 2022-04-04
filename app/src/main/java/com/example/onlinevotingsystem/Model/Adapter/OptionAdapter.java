package com.example.onlinevotingsystem.Model.Adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinevotingsystem.Model.Topic;
import com.example.onlinevotingsystem.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.ViewHolder> {

    private Topic dataModalTopic = new Topic();

    // pass in topic variable into OptionAdapter constructor
    public OptionAdapter(Topic dataModalTopic) {
        this.dataModalTopic = dataModalTopic;
    }

    @Override
    public OptionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        return new OptionAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_dynamic_option_buttons, parent, false));
    }

    @Override
    public void onBindViewHolder(OptionAdapter.ViewHolder holder, int position) {
        // get the data model based on position
        Map<String, Integer> options = dataModalTopic.getOptions();
        Set<String> keySet = dataModalTopic.getOptions().keySet();
        // keys to ArrayList
        ArrayList<String> listOfKeys = new ArrayList<String>(keySet);
        // values to Collection
        Collection<Integer> values = options.values();
        // convert Collection to List so that we can get values by indexing with position
        List listOfValues = new ArrayList(values);

        Switch button = holder.dynamicOptionButton;
        // name each button
        button.setText(listOfKeys.get(position));
        // give each button tag the value to store for its key
        button.setTag(listOfValues.get(position));
        Log.d("OptionAdapter", "Button tag value is: " + button.getTag());
        // switch is on if the value is 1 (i.e. if it is selected)
        if((int) button.getTag() == 1) {
            button.setChecked(true);
        }
        Log.d("OptionAdapter", "switch checked: " + button.isChecked());
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
