package com.example.converterapp.currency.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.converterapp.R;
import com.example.converterapp.currency.model.CurrencyModel;

import java.util.List;

public class CurrencyItemAdapter extends RecyclerView.Adapter<CurrencyItemAdapter.CurrencyItemViewHolder> {
    private List<CurrencyModel> dataset;

    public CurrencyItemAdapter(List<CurrencyModel> dataset) {
        this.dataset = dataset;
    }

    @Override
    public CurrencyItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CurrencyItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyItemAdapter.CurrencyItemViewHolder holder, int position) {
        holder.flagImage.setImageResource(dataset.get(position).getFlagImage());
        holder.currencyName.setText(dataset.get(position).getCurrencyName());
        holder.currencyLongName.setText(dataset.get(position).getCurrencyLongName());
        holder.currencySymbol.setText(dataset.get(position).getCurrencySymbol());
        holder.currencyAmount.setText(Double.toString(dataset.get(position).getCurrencyAmount()));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class CurrencyItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView flagImage;
        private TextView currencyName;
        private TextView currencyLongName;
        private TextView currencySymbol;
        private TextView currencyAmount;

        public CurrencyItemViewHolder(View itemView) {
            super(itemView);
            currencyName = itemView.findViewById(R.id.currencyName);
            currencyLongName = itemView.findViewById(R.id.currencyLongName);
            currencySymbol = itemView.findViewById(R.id.currencySymbol);
            currencyAmount = itemView.findViewById(R.id.currencyAmount);
            flagImage = itemView.findViewById(R.id.flagImage);
        }
    }
}
