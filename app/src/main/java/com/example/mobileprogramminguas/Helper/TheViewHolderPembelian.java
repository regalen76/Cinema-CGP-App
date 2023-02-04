package com.example.mobileprogramminguas.Helper;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileprogramminguas.R;

public class TheViewHolderPembelian extends RecyclerView.ViewHolder {

    TextView invoice,item,duration,method;

    public TheViewHolderPembelian(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);

        invoice = itemView.findViewById(R.id.invoicecell);
        item = itemView.findViewById(R.id.itemcell);
        duration = itemView.findViewById(R.id.durationcell);
        method = itemView.findViewById(R.id.methodcell);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recyclerViewInterface != null){
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClick(pos);
                    }
                }
            }
        });
    }
}
