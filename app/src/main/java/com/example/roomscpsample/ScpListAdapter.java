package com.example.roomscpsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScpListAdapter extends RecyclerView.Adapter<ScpListAdapter.ScpViewHolder>{
    private final LayoutInflater mInflater;
    private List<Scp> mScps; // Cached copy of words

    ScpListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ScpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ScpViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ScpViewHolder holder, int position) {
        if (mScps != null) {
            Scp current = mScps.get(position);
            holder.scpItemView.setText(current.getScp());
        } else {
            // Covers the case of data not being ready yet.
            holder.scpItemView.setText("No Scp");
        }
    }

    void setScps(List<Scp> scps){
        mScps = scps;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mScps has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mScps != null)
            return mScps.size();
        else return 0;
    }

    class ScpViewHolder extends RecyclerView.ViewHolder {
        private final TextView scpItemView;

        private ScpViewHolder(View itemView) {
            super(itemView);
            scpItemView = itemView.findViewById(R.id.textView);
        }
    }
    public Scp getScpAtPosition (int position) {
        return mScps.get(position);
    }
}
