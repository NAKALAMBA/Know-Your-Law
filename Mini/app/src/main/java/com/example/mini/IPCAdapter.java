package com.example.mini;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IPCAdapter extends RecyclerView.Adapter<IPCAdapter.IPCViewHolder> {

    private List<IPCSection> ipcList;

    public IPCAdapter(List<IPCSection> ipcList) {
        this.ipcList = ipcList;
    }

    @Override
    public IPCViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ipc, parent, false);
        return new IPCViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IPCViewHolder holder, int position) {
        IPCSection ipc = ipcList.get(position);
        holder.description.setText("Description: " + ipc.getDescription());
        holder.offense.setText("Offense: " + ipc.getOffense());
        holder.punishment.setText("Punishment: " + ipc.getPunishment());
    }

    @Override
    public int getItemCount() {
        return ipcList.size();
    }

    public static class IPCViewHolder extends RecyclerView.ViewHolder {
        public TextView description, offense, punishment;

        public IPCViewHolder(View view) {
            super(view);
            description = view.findViewById(R.id.description);
            offense = view.findViewById(R.id.offense);
            punishment = view.findViewById(R.id.punishment);
        }
    }
}
