package com.aahuf.converterapp.currency.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aahuf.converterapp.R;
import com.aahuf.converterapp.currency.model.CurrencyModel;

import java.util.List;

public class CurrencyItemAdapter extends RecyclerView.Adapter<CurrencyItemAdapter.CurrencyItemViewHolder> {
    private final List<CurrencyModel> dataset;

    public CurrencyItemAdapter(List<CurrencyModel> dataset) {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public CurrencyItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_item, parent, false);
        return new CurrencyItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyItemAdapter.CurrencyItemViewHolder holder, int position) {
        holder.flagImage.setImageResource(dataset.get(position).getFlagImage());
        holder.currencyName.setText(dataset.get(position).getCurrencyName());
        holder.currencyLongName.setText(dataset.get(position).getCurrencyLongName());
        holder.currencySymbol.setText(dataset.get(position).getCurrencySymbol());
        holder.currencyAmount.setText(dataset.get(position).getCurrencyAmount().toString());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class CurrencyItemViewHolder extends RecyclerView.ViewHolder {

        private final ImageView flagImage;
        private final TextView currencyName;
        private final TextView currencyLongName;
        private final TextView currencySymbol;
        private final TextView currencyAmount;

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
