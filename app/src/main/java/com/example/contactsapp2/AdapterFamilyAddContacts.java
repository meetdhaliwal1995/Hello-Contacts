package com.example.contactsapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterFamilyAddContacts extends RecyclerView.Adapter<AdapterFamilyAddContacts.ViewHolder> {

    private List<PhnContacts> _list;
    private Context context;

    public AdapterFamilyAddContacts(List<PhnContacts> _list, Context context) {
        this._list = _list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.frag_2add_contacts, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhnContacts list = _list.get(position);
        holder.name.setText(list.getName());

    }

    @Override
    public int getItemCount()
    {
        return _list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_tv);
        }
    }
}
