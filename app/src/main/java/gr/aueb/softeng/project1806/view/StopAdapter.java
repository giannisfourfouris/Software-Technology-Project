package gr.aueb.softeng.project1806.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import gr.aueb.softeng.project1806.R;
import gr.aueb.softeng.project1806.model.Stop;
import gr.aueb.softeng.project1806.presenter.ItemSelectionListener;

public class StopAdapter extends RecyclerView.Adapter<StopAdapter.ViewHolder>{

    private List<Stop> itemList;

    private ItemSelectionListener<Stop> stopSelectionListener;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewGroup listItem;
        public TextView stopInfoText;
        public ImageButton btnTempAddStop, btnTempRemoveStop;

        public ViewHolder(ViewGroup v) {
            super(v);
            listItem = v;
            stopInfoText = listItem.findViewById(R.id.StopInfotext);
            btnTempAddStop = listItem.findViewById(R.id.TempAddStopButton);
            btnTempRemoveStop = listItem.findViewById(R.id.TempRemoveStopButton);
        }
    }


    public StopAdapter(List<Stop> myDataSet){
        itemList = myDataSet;
    }


    public void setStopSelectionListener (ItemSelectionListener<Stop> stopSelectionListener){
        this.stopSelectionListener = stopSelectionListener;
    }

    public ItemSelectionListener<Stop> getStopSelectionListener() {
        return stopSelectionListener;
    }

    @Override
    public StopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_stop_result, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        // - get element from your dataset at this position
        final Stop stopAtPosition = itemList.get(position);

        // - replace the contents of the view with data from the dataset item at this position
        holder.stopInfoText.setText(stopAtPosition.getStopID() + " " + stopAtPosition.getStopName());
        holder.btnTempAddStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // notify the Activity of the selected stop
                holder.btnTempAddStop.setImageResource(R.drawable.plus_disabled);
                holder.btnTempAddStop.setEnabled(false);
                holder.btnTempRemoveStop.setEnabled(true);
                holder.btnTempRemoveStop.setImageResource(R.drawable.minus);
                if (stopSelectionListener != null) {
                    stopSelectionListener.onItemSelected(stopAtPosition, v.getId());
                }
            }
        });
        holder.btnTempRemoveStop.setEnabled(false);
        holder.btnTempRemoveStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // notify the Activity of the selected stop
                holder.btnTempRemoveStop.setImageResource(R.drawable.minus_disabled);
                holder.btnTempRemoveStop.setEnabled(false);
                holder.btnTempAddStop.setEnabled(true);
                holder.btnTempAddStop.setImageResource(R.drawable.plus);
                if (stopSelectionListener != null) {
                    stopSelectionListener.onItemSelected(stopAtPosition, v.getId());
                }
            }
        });

    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
