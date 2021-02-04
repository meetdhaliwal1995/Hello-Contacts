package com.example.contactsapp2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private List<PhnContacts> _list;
    private Context context;

    public ContactsAdapter(List<PhnContacts> _list, Context context) {
        this._list = _list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.frag_1,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhnContacts list = _list.get(position);
        holder.name.setText(list.getName());
        holder.number.setText(list.getNumber());

    }

    @Override
    public int getItemCount() {
        return _list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,number;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.contact);
        number = itemView.findViewById(R.id.number);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ContactDetailInfo.class);
                intent.putExtra("contact_name",_list.get(getAdapterPosition()).getName());
                intent.putExtra("contact_number",_list.get(getAdapterPosition()).getNumber());
                intent.putExtra("contact_id",_list.get(getAdapterPosition()).getId());
                context.startActivity(intent);
            }
        });
    }
}
}
