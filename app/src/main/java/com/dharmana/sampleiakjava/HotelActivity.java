package com.dharmana.sampleiakjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HotelActivity extends AppCompatActivity {

    String area;
    Integer dewasa, anak, tamu, kamar;

    ListView listHotel;
    List<String> dataHotel = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listHotel = (ListView) findViewById(R.id.list_hotel);


        area    = getIntent().getStringExtra("area");
        dewasa  = getIntent().getIntExtra("dewasa", 0);
        anak    = getIntent().getIntExtra("anak", 0);
        tamu    = getIntent().getIntExtra("tamu", 0);
        kamar   = getIntent().getIntExtra("kamar", 0);


        switch (area) {
            case "Sanur":
                dataHotel.add("Grand Inna Bali Beach");
                dataHotel.add("Hotel Sindhu");
                dataHotel.add("The Griya Hotel Sanur");
                dataHotel.add("Sanur Paradise Hotel");
                dataHotel.add("Taksu Sanur Hotel");
                break;
            case "Kuta":
                dataHotel.add("Pullman Kuta");
                dataHotel.add("Harris Kuta");
                dataHotel.add("Hotel Kuta");
                break;
            case "Ubud":
                dataHotel.add("Ubud Villa");
                dataHotel.add("Grand Pita Maha Resort");
                dataHotel.add("Padma Resort Ubud");
                dataHotel.add("The Evitel Resort Ubud");
                dataHotel.add("Green View Private Villas");
                break;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                dataHotel
        );

        listHotel.setAdapter(adapter);

        getSupportActionBar().setTitle(area);
        getSupportActionBar().setSubtitle("Dewasa: "+dewasa+", Anak: "+anak);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listHotel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String namaHotel = dataHotel.get(position);

                String shareText = "[TEST] Saya akan berkunjung ke "+area+" dan menginap di "+namaHotel+" dengan " +
                        "jumlah dewasa "+dewasa+" dan anak "+anak;


                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                shareIntent.setType("text/plain");
                startActivity(shareIntent);

            }
        });



    }

}
