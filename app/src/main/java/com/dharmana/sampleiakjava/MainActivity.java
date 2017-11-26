package com.dharmana.sampleiakjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtDewasa, edtAnak;
    Button btnSubmit;

    String area;
    Integer dewasa, anak, kamar, tamu;

    Boolean resto = true;

    Spinner spArea, spTamu, spKamar;
    List<String> listArea = new ArrayList<String>();
    List<Integer> listTamu = new ArrayList<Integer>();
    List<Integer> listKamar = new ArrayList<Integer>();

    RadioButton rdoRestoYa, rdoRestoTdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Deklarasi variabel
        btnSubmit           = (Button) findViewById(R.id.btn_submit);

        edtDewasa           = (EditText) findViewById(R.id.edt_dewasa);
        edtAnak             = (EditText) findViewById(R.id.edt_anak);

        spArea              = (Spinner) findViewById(R.id.sp_area);
        spTamu              = (Spinner) findViewById(R.id.sp_tamu);
        spKamar              = (Spinner) findViewById(R.id.sp_kamar);

        rdoRestoYa           = (RadioButton) findViewById(R.id.rdo_resto_ya);
        rdoRestoTdk            = (RadioButton) findViewById(R.id.rdo_resto_tdk);

        for (int i=1; i<=10; i++) {
            listTamu.add(i);
        }

        for (int i=1; i<=15; i++) {
            listKamar.add(i);
        }


        /* Penambahan data */
        listArea.add("Sanur");
        listArea.add("Kuta");
        listArea.add("Canggu");
        listArea.add("Ubud");
        listArea.add("Candidasa");

        ArrayAdapter<String> areaAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, listArea);

        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spArea.setAdapter(areaAdapter);



        ArrayAdapter<Integer> tamuAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, listTamu);
        tamuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTamu.setAdapter(tamuAdapter);


        ArrayAdapter<Integer> kamarAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, listKamar);
        tamuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKamar.setAdapter(kamarAdapter);


        spArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                area = listArea.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spTamu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tamu = listTamu.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spKamar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kamar = listKamar.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        rdoRestoYa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resto = rdoRestoYa.isChecked();
            }
        });

        rdoRestoTdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resto = rdoRestoYa.isChecked();
            }
        });




        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean valid = true;


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




                if(valid) {

                    String restoText;

                    if(resto) {
                        restoText = "Ya";
                    } else {
                        restoText = "Tidak";
                    }

                    Intent intentHotel = new Intent(MainActivity.this, HotelActivity.class);
                    intentHotel.putExtra("area", area);
                    intentHotel.putExtra("dewasa", dewasa);
                    intentHotel.putExtra("anak", anak);
                    intentHotel.putExtra("tamu", tamu);
                    intentHotel.putExtra("kamar", kamar);
                    startActivity(intentHotel);


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
