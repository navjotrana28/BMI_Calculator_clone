package com.example.bmicalculator;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Color;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spin2;
        ArrayAdapter adapter2;
        final Spinner sp;
        final TextView kilogram = (TextView) findViewById(R.id.kilogram_Syntax);
        Button goButton = (Button)findViewById(R.id.go);
        final LinearLayout linearResult =(LinearLayout)findViewById(R.id.result_layout);
        final View v = findViewById(R.id.include);
        final GridLayout gridLayoutnew =(GridLayout)findViewById(R.id.grid_layout);
        spin2=(Spinner)findViewById(R.id.spinner2);
        sp = (Spinner) findViewById(R.id.spinner1);
        final TextView centimeter_textview=(TextView)findViewById(R.id.centimeter_textview);
        final ArrayList<Button> list = new ArrayList<>();
        final Button zero = (Button) findViewById(R.id.zero);
        list.add(zero);
        final Button one = (Button) findViewById(R.id.one);
        list.add(one);
        final Button two = (Button) findViewById(R.id.two);
        list.add(two);
        final Button three = (Button) findViewById(R.id.three);
        list.add(three);
        final Button four = (Button) findViewById(R.id.four);
        list.add(four);
        final Button five = (Button) findViewById(R.id.five);
        list.add(five);
        final Button six = (Button) findViewById(R.id.six);
        list.add(six);
        final Button seven = (Button) findViewById(R.id.seven);
        list.add(seven);
        final Button eight = (Button) findViewById(R.id.eight);
        list.add(eight);
        final Button nine = (Button) findViewById(R.id.nine);
        list.add(nine);


        final TextView meter =(TextView)findViewById(R.id.centimeter_Syntax);
//for change of color of weight and height..
        final TextView sixty, weightNumber;

        sixty = (TextView) findViewById(R.id.kilogram_textview);
        weightNumber = (TextView) findViewById(R.id.centimeter_textview);
        ConstraintLayout weight_measure_layout = (ConstraintLayout) findViewById(R.id.weight_measure_layout);
        ConstraintLayout height_measure_layout = (ConstraintLayout) findViewById(R.id.height_measure_layout);
        ConstraintLayout weight_left_layout = (ConstraintLayout) findViewById(R.id.weight_Left_Layout);
        ConstraintLayout height_left_layout = (ConstraintLayout) findViewById(R.id.height_left_Layout);


        weight_measure_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sixty.setTextColor(Color.RED);
                weightNumber.setTextColor(getResources().getColor(R.color.colorDark_grey2));
                gridLayoutnew.setVisibility(View.VISIBLE);

            }
        });
        height_measure_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                centimeter_textview.setTextColor(Color.RED);
                sixty.setTextColor(getResources().getColor(R.color.colorDark_grey2));
                gridLayoutnew.setVisibility(View.VISIBLE);
//                linearResult.setVisibility(view.GONE);

            }
        });


        for(int i=0;i<10;i++) {

            final int finalI = i;
            list.get(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            list.get(finalI).setBackgroundColor(getResources().getColor(R.color.colorDark_grey2));
                            // PRESSED
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            list.get(finalI).setBackgroundColor(Color.WHITE);
                            // RELEASED
                            break;
                    }
                    return false;
                }
            });
        }
        //Spinners=------------
        final String[] weightlist = {"Kilogram", "pound"};
        final String[] heightlist = {"Centimeter", "Meter","Feet","Inch"};

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, weightlist);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kilogram.setText(weightlist[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, heightlist);
        spin2.setAdapter(adapter2);
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                meter.setText(heightlist[i]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        weight_left_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.performClick();
            }
        });
        height_left_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spin2.performClick();

            }
        });
        //GO button for result-----------

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gridLayoutnew.setVisibility(view.GONE);
                v.setVisibility(view.VISIBLE);

            }
        });




    }
}