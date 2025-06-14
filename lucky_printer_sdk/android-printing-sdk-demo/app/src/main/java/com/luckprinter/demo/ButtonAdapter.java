package com.luckprinter.demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.luckjingle.printersdk.R;
import com.luckprinter.demo.bean.ButtonItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ButtonAdapter extends RecyclerView.Adapter {
    private List<ButtonItem> buttonList = new ArrayList<>();
    private ItemClickListener itemClickListener;

    public ButtonAdapter(List<ButtonItem> buttonList) {
        this.buttonList = buttonList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_button, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Holder hd = (Holder) holder;
        ButtonItem item = buttonList.get(position);
        hd.setContent(item);

        hd.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return buttonList.size();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener{
        public void onItemClick(ButtonItem btn);
    }

    class Holder extends RecyclerView.ViewHolder{
        Button btn;
        public Holder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn);
        }

        public void setContent(ButtonItem item) {
            btn.setText(item.getName());
        }
    }
}
