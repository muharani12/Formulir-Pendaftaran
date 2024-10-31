package com.example.formulirpendaftaran;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPhone;
    private RadioGroup radioGroupGender;
    private CheckBox checkboxHobby1, checkboxHobby2, checkboxHobby3, checkboxHobby4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Menghubungkan elemen UI
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        checkboxHobby1 = findViewById(R.id.checkboxHobby1);
        checkboxHobby2 = findViewById(R.id.checkboxHobby2);
        checkboxHobby3 = findViewById(R.id.checkboxHobby3);
        checkboxHobby4 = findViewById(R.id.checkboxHobby4);

        Button buttonSubmit = findViewById(R.id.buttonSubmit);

        // Mengatur listener untuk tombol kirim
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }

    private void submitForm() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();

        // Cek apakah semua field sudah diisi
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Silakan isi semua field.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Mendapatkan jenis kelamin
        String gender = ((RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();

        // Mendapatkan hobi yang dipilih
        StringBuilder hobbies = new StringBuilder();
        if (checkboxHobby1.isChecked()) hobbies.append(checkboxHobby1.getText()).append(", ");
        if (checkboxHobby2.isChecked()) hobbies.append(checkboxHobby2.getText()).append(", ");
        if (checkboxHobby3.isChecked()) hobbies.append(checkboxHobby3.getText()).append(", ");
        if (checkboxHobby4.isChecked()) hobbies.append(checkboxHobby4.getText()).append(", ");

        // Hapus koma dan spasi terakhir
        if (hobbies.length() > 0) {
            hobbies.setLength(hobbies.length() - 2);
        } else {
            hobbies.append("Tidak ada hobi yang dipilih");
        }

        showDialog(name, email, phone, gender, hobbies.toString());
    }

    private void showDialog(String name, String email, String phone, String gender, String hobbies) {
        new AlertDialog.Builder(this)
                .setTitle("Data Pendaftaran")
                .setMessage("Nama: " + name + "\nEmail: " + email + "\nTelepon: " + phone +
                        "\nJenis Kelamin: " + gender + "\nHobi: " + hobbies)
                .setPositiveButton("OK", null)
                .show();
    }
}
