package com.lasmawitaolivianainggolan.utslasmawitaolivianainggolan;

package com.example.agendaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddAgendaActivity extends AppCompatActivity {

    private EditText etNamaAgenda, etDeskripsi;
    private Spinner spinnerStatus;
    private Button btnSimpan;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_agenda);

        // Inisialisasi elemen UI
        etNamaAgenda = findViewById(R.id.etNamaAgenda);
        etDeskripsi = findViewById(R.id.etDeskripsi);
        spinnerStatus = findViewById(R.id.spinnerStatus);
        btnSimpan = findViewById(R.id.btnSimpan);

        // Inisialisasi database helper
        databaseHelper = new DatabaseHelper(this);

        // Set listener untuk tombol simpan
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAgenda();
            }
        });
    }

    private void saveAgenda() {
        // Ambil data dari inputan
        String namaAgenda = etNamaAgenda.getText().toString().trim();
        String deskripsi = etDeskripsi.getText().toString().trim();
        String status = spinnerStatus.getSelectedItem().toString();

        // Validasi input
        if (namaAgenda.isEmpty() || deskripsi.isEmpty()) {
            Toast.makeText(this, "Mohon isi semua kolom", Toast.LENGTH_SHORT).show();
            return;
        }

        // Simpan data ke database
        boolean isInserted = databaseHelper.insertAgenda(namaAgenda, deskripsi, status);

        if (isInserted) {
            Toast.makeText(this, "Agenda berhasil disimpan", Toast.LENGTH_SHORT).show();
            finish(); // Kembali ke aktivitas sebelumnya
        } else {
            Toast.makeText(this, "Gagal menyimpan agenda", Toast.LENGTH_SHORT).show();
        }
    }
}
