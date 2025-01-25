package com.example.ruralagriboost;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CropAdapter extends RecyclerView.Adapter<CropAdapter.CropViewHolder> {

    private final List<Crop> cropList;
    private final OnCropClickListener listener;

    public CropAdapter(List<Crop> cropList, OnCropClickListener listener) {
        this.cropList = cropList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CropViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crop, parent, false);
        return new CropViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CropViewHolder holder, int position) {
        Crop crop = cropList.get(position);
        holder.tvCropName.setText(crop.getName());
        holder.tvCropDescription.setText(crop.getDescription());
        holder.ivCropImage.setImageResource(crop.getImageResource());
        holder.btnViewDetails.setOnClickListener(v -> listener.onCropClick(crop));
    }

    @Override
    public int getItemCount() {
        return cropList.size();
    }

    static class CropViewHolder extends RecyclerView.ViewHolder {
        TextView tvCropName, tvCropDescription;
        ImageView ivCropImage;
        Button btnViewDetails;

        public CropViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCropName = itemView.findViewById(R.id.tv_crop_name);
            tvCropDescription = itemView.findViewById(R.id.tv_crop_description);
            ivCropImage = itemView.findViewById(R.id.iv_crop_image);
            btnViewDetails = itemView.findViewById(R.id.btn_view_details);
        }
    }

    public interface OnCropClickListener {
        void onCropClick(Crop crop);
    }
}
