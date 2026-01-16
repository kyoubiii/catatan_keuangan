package com.duid.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duid.R;
import com.duid.model.Transaksi;
import java.util.List;

// mengurus tampilan list
// Bayangkan kamu punya sekantong data transaksi (List Java),
// tapi RecyclerView (Daftar di Layar HP) tidak mengerti cara menampilkan data mentah tersebut.
// Nah, Adapter inilah yang bertugas mengambil satu per satu data transaksi,
// lalu menyusunnya ke dalam tampilan kotak (layout XML) agar bisa dilihat oleh pengguna.
public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.ViewHolder> {

    private List<Transaksi> listTransaksi;
    private OnTransaksiClickListener listener; // Abstraksi listener

    // Interface tunggal untuk semua aksi klik (Interface Segregation)
    public interface OnTransaksiClickListener {
        void onEdit(Transaksi transaksi);
        void onDelete(Transaksi transaksi);
    }

    public void setOnTransaksiClickListener(OnTransaksiClickListener listener) {
        this.listener = listener;
    }

    public TransaksiAdapter(List<Transaksi> listTransaksi) {
        this.listTransaksi = listTransaksi;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaksi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaksi t = listTransaksi.get(position);

        // Pilar Encapsulation, Memanfaatkan logika internal objek
        holder.tvKeterangan.setText(t.getKeterangan());
        holder.tvSumber.setText(t.getSumber());
        holder.tvJumlah.setText(t.getFormattedJumlah());
        holder.tvJumlah.setTextColor(t.getColorByJenis());

        // Listener Edit (Klik Biasa)
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onEdit(t); // Delegasi tanggung jawab
        });

        // Listener Hapus (Klik Lama)
        holder.itemView.setOnLongClickListener(v -> {
            if (listener != null) listener.onDelete(t);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return listTransaksi.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvKeterangan, tvSumber, tvJumlah;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKeterangan = itemView.findViewById(R.id.tv_item_keterangan);
            tvSumber = itemView.findViewById(R.id.tv_item_sumber);
            tvJumlah = itemView.findViewById(R.id.tv_item_jumlah);
        }
    }
}