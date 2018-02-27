package com.example.tandels.issapplication.viewmodel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tandels.issapplication.R;
import com.example.tandels.issapplication.data.model.Response;

import java.util.List;

import static com.example.tandels.issapplication.viewmodel.AppConstants.MULTIPLIER_1000;

/**
 *ResponseDataAdapter class
 */
public class ResponseDataAdapter extends RecyclerView.Adapter<ResponseDataAdapter.MyViewHolder> {

    private List<Response> responseList;
    private Context context;

    public ResponseDataAdapter(List<Response> responseList, Context context) {

        this.responseList = responseList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Response response = responseList.get(position);
        holder.durationText.setText(context.getString(R.string.string_duration) + (Utility.formateDuration(response.getDuration())));
        holder.risetimeText.setText(Utility.formateDateTime(response.getRisetime() * MULTIPLIER_1000));
    }

    @Override
    public int getItemCount() {
        return responseList == null ? 0 : responseList.size();
    }

   /* public void updateAnswers(List<Response> items) {
        responseList = items;
        notifyDataSetChanged();
    }*/

    private Response getItems(int adapterPosition) {
        return responseList.get(adapterPosition);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView durationText;
        TextView risetimeText;

        public MyViewHolder(View listitemView) {
            super(listitemView);
            durationText = (TextView) listitemView.findViewById(R.id.duration_text);
            risetimeText = (TextView) listitemView.findViewById(R.id.risetime_text);
        }
    }
}
