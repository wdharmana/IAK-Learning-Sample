package com.dharmana.sampleiakjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtArea, edtDewasa, edtAnak;
    Button btnSubmit;

    String area;
    Integer dewasa, anak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Deklarasi variabel
        btnSubmit           = (Button) findViewById(R.id.btn_submit);
        edtArea             = (EditText) findViewById(R.id.edt_area);
        edtDewasa           = (EditText) findViewById(R.id.edt_dewasa);
        edtAnak             = (EditText) findViewById(R.id.edt_anak);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean valid = true;

                area = edtArea.getText().toString();

                if(!TextUtils.isEmpty(edtDewasa.getText().toString())) {
                    dewasa = Integer.valueOf(edtDewasa.getText().toString());

                    if(dewasa<1) {
                        edtDewasa.setError("Dewasa minimal 1!");
                        valid = false;
                    }
                } else {
                    valid = false;
                    edtDewasa.setError("Tidak boleh kosong!");
                }


                if(!TextUtils.isEmpty(edtAnak.getText().toString())) {
                    anak = Integer.valueOf(edtAnak.getText().toString());

                    if(anak<0) {
                        edtAnak.setError("Tidak boleh < 0");
                        valid = false;
                    }

                } else {
                    valid = false;
                    edtDewasa.setError("Tidak boleh kosong!");
                }



                if(TextUtils.isEmpty(area)) {
                    valid = false;
                    edtArea.setError("Area wajib diisi!");
                }

                if(valid) {
                    Integer jumlah = hitungOrang(dewasa, anak);
                    showToast("Hasil Pencarian berlokasi di: " + area + ", jumlah: " + jumlah);

                } else {
                    showToast("Mohon dicek kembali!");
                }
            }
        });

    }

    private void showToast(String content) {
        Toast.makeText(MainActivity.this, content, Toast.LENGTH_LONG).show();
    }

    private Integer hitungOrang(Integer dewasa, Integer anak) {
        Integer jumlah = dewasa + anak;
        return jumlah;
    }


}
