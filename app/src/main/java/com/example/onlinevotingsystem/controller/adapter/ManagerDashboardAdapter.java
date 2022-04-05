package com.example.onlinevotingsystem.controller.adapter;

import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinevotingsystem.model.Topic;
import com.example.onlinevotingsystem.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ManagerDashboardAdapter extends RecyclerView.Adapter<ManagerDashboardAdapter.ViewHolder> {

    private ArrayList<Topic> dataModalTopic = new ArrayList<>();

    // pass in topic variable into StatisticAdapter constructor
    public ManagerDashboardAdapter(ArrayList<Topic> dataModalTopic) {
        this.dataModalTopic = dataModalTopic;
    }

    @Override
    public ManagerDashboardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        return new ManagerDashboardAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_dynamic_topic_statistics, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Topic currentTopic = dataModalTopic.get(position);

        // extract options to map
        Map<String, Integer> options = currentTopic.getOptions();
        // extract keys from options
        Set<String> keySet = currentTopic.getOptions().keySet();
        // send keys to ArrayList
        ArrayList<String> listOfKeys = new ArrayList<String>(keySet);
        // extract values to Collection
        Collection<Integer> values = options.values();
        // convert Collection to List so that we can get values by indexing with position
        List listOfValues = new ArrayList(values);

        // button to show topic title
        Button button = holder.statisticText;
        button.setText(currentTopic.getTitle());
        // textview to show options and their values
        EditText text = holder.statisticOptions;
        // loop through each option to add to textview
        for(int i=0;i<listOfKeys.size();i++) {
            text.append(listOfKeys.get(i) + ": " + listOfValues.get(i) + "     ");
        }

    }

    @Override
    public int getItemCount() {
        return dataModalTopic.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Button statisticText;
        public EditText statisticOptions;

        public ViewHolder(View itemView) {
            super(itemView);
            statisticText = itemView.findViewById(R.id.topicStatisticButton);
            statisticText.setInputType(InputType.TYPE_NULL);
            statisticOptions = itemView.findViewById(R.id.editTextMultiLineStatistics);
        }
    }
}

