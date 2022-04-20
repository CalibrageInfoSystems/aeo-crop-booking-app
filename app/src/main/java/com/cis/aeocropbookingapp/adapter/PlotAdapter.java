package com.cis.aeocropbookingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cis.aeocropbookingapp.R;
import com.cis.aeocropbookingapp.models.CropData;

import java.util.ArrayList;
import java.util.List;

public class PlotAdapter extends RecyclerView.Adapter<PlotAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<CropData> cropData = new ArrayList<>();
    private Context ctx;

    public PlotAdapter(Context _ctx, List<CropData> _cropData) {
        this.mInflater = LayoutInflater.from(_ctx);
        this.cropData = _cropData;
        this.ctx = _ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myTextView.setText( "Survey Number : "+cropData.get(position).getSeedCompanyName());
    }

    @Override
    public int getItemCount() {

        if (cropData == null || cropData.size() == 0)
            return 0;
        else
            return cropData.size();


    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvAnimalName);
        }
    }
}
