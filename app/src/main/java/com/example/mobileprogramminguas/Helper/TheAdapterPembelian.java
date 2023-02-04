package com.example.mobileprogramminguas.Helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileprogramminguas.Pembelian;
import com.example.mobileprogramminguas.R;

import java.util.List;

public class TheAdapterPembelian extends RecyclerView.Adapter<TheViewHolderPembelian> {

    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    List<Pembelian> pembelians;

    public TheAdapterPembelian(RecyclerViewInterface recyclerViewInterface,Context context, List<Pembelian> pembelians) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.pembelians = pembelians;
    }

    @NonNull
    @Override
    public TheViewHolderPembelian onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_pembelian,parent,false);
        return new TheViewHolderPembelian(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull TheViewHolderPembelian holder, int position) {
        holder.invoice.setText("Invoice: "+pembelians.get(position).getInvoice());
        holder.item.setText(pembelians.get(position).getTitle());
        holder.duration.setText(pembelians.get(position).getDuration());
        holder.method.setText("Method: "+pembelians.get(position).getMethod());
    }

    @Override
    public int getItemCount() {
        return pembelians.size();
    }
}
