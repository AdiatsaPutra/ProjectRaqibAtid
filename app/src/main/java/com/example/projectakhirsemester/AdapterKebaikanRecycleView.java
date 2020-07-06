package com.example.projectakhirsemester;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterKebaikanRecycleView extends RecyclerView.Adapter<AdapterKebaikanRecycleView.ViewHolder> {
    ReadKebaikan listener;
    private ArrayList<Request> request;
    private Context context;

    public AdapterKebaikanRecycleView(ArrayList<Request> requests, Context ctx){
        request = requests;
        context = ctx;
        listener = (ReadKebaikan)ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul;
        TextView tvKeterangan;

        ViewHolder(View v) {
            super(v);
            tvJudul = (TextView) v.findViewById(R.id.Judul);
            tvKeterangan = (TextView) v.findViewById(R.id.Keterangan);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kebaikan, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String judul = request.get(position).getJudul();
        final String keterangan = request.get(position).getKeterangan();
        holder.tvJudul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        holder.tvJudul.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_view);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button editButton = (Button) dialog.findViewById(R.id.bt_edit_data);
                Button delButton = (Button) dialog.findViewById(R.id.bt_delete_data);

                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                context.startActivity(Add.getActIntent((Activity) context).putExtra("Data", request.get(position)));
                            }
                        }
                );


                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                listener.onDeleteData(request.get(position), position);

                            }
                        }
                );
                return true;
            }

        });
        holder.tvJudul.setText(judul);
        holder.tvKeterangan.setText(keterangan);
}

    @Override
    public int getItemCount() {
        return request.size();
    }

//    public interface FirebaseDataListener{
//        void onDeleteData(Request request, int position);
//    }
}
