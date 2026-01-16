package com.duid.ui;

import android.os.Bundle;
import android.widget.TextView;
import com.duid.R;
import com.duid.logic.SaldoCash;
import com.duid.logic.SaldoEwallet;

public class SaldoActivity extends BaseActivity {
    private TextView tvCash, tvEwallet, tvTotalSemua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saldo);

        // PERBAIKAN: Hubungkan variabel dengan ID di XML
        // Pastikan ID ini (tv_saldo_cash, dll) sama persis dengan yang ada di activity_saldo.xml
        tvCash = findViewById(R.id.tv_saldo_cash);
        tvEwallet = findViewById(R.id.tv_saldo_ewallet);
        tvTotalSemua = findViewById(R.id.tv_total_semua);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilkanSaldo();
    }

    private void tampilkanSaldo() {
        // Cek keamanan agar tidak null
        if (tvCash == null || tvEwallet == null || tvTotalSemua == null) return;

        double cash = viewModel.getBalance(new SaldoCash());
        double ewallet = viewModel.getBalance(new SaldoEwallet());
        double totalSemua = cash + ewallet;

        tvCash.setText(formatCurrency(cash));
        tvEwallet.setText(formatCurrency(ewallet));
        tvTotalSemua.setText("Total Semua: " + formatCurrency(totalSemua));
    }

    private String formatCurrency(double amount) {
        return "Rp " + String.format("%,.0f", amount);
    }
}