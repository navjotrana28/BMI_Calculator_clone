package com.example.bmicalculator;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

       int flagOfWeight =1, flagOfHeight =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String numberButton[]={"0","1","2","3","4","5","6","7","8","9"};
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
        final TextView weight_TextField;

        weight_TextField = (TextView) findViewById(R.id.kilogram_textview);
        ConstraintLayout weight_measure_layout = (ConstraintLayout) findViewById(R.id.weight_measure_layout);
        ConstraintLayout height_measure_layout = (ConstraintLayout) findViewById(R.id.height_measure_layout);
        final ConstraintLayout weight_left_layout = (ConstraintLayout) findViewById(R.id.weight_Left_Layout);
        final ConstraintLayout height_left_layout = (ConstraintLayout) findViewById(R.id.height_left_Layout);
         final TextView weight_Number=(TextView)findViewById(R.id.kilogram_textview);
         final TextView height_TextField=(TextView)findViewById(R.id.centimeter_textview);
//
//        final Spinner spinner_Weight= (Spinner) findViewById(R.id.spinner1);
//        final Spinner spinner_height= (Spinner) findViewById(R.id.spinner2);
         //result layout initialization--------
        final TextView display_ResultTextView=(TextView)findViewById(R.id.display_result);
        final TextView normal_under_overWeight=(TextView)findViewById(R.id.normal);
        final TextView centimeter_Result=(TextView)findViewById(R.id.centimeter_Syntax);
        final TextView kilogram_Result=(TextView)findViewById(R.id.kilogram_Syntax);
        final TextView oneText_clear=(TextView)findViewById((R.id.hash));



        weight_measure_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight_TextField.setTextColor(Color.RED);
                height_TextField.setTextColor(getResources().getColor(R.color.colorDark_grey2));
                gridLayoutnew.setVisibility(View.VISIBLE);
                flagOfWeight =1;
                flagOfHeight =0;
            }
        });
        height_measure_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                centimeter_textview.setTextColor(Color.RED);
                weight_TextField.setTextColor(getResources().getColor(R.color.colorDark_grey2));
                gridLayoutnew.setVisibility(View.VISIBLE);
//                linearResult.setVisibility(view.GONE);
                    flagOfWeight=0;
                    flagOfHeight=1;
            }
        });


        for(int i=0;i<10;i++) {

            final int finalI = i;
            list.get(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            list.get(finalI).setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
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
        final String[] weightlist = {"Kilogram", "Pound"};
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

                double weighTemp = 0, heightTemp = 0;
                if (kilogram_Result.getText().toString().equals("Kilogram")) {
                    weighTemp = Double.parseDouble(weight_Number.getText().toString());
                } else if (kilogram_Result.getText().toString().equals("Pound")) {
                    weighTemp = Double.parseDouble(weight_Number.getText().toString()) / 0.4535;
                }
                if (centimeter_Result.getText().toString().equals("Centimeter")) {
                    heightTemp = Double.parseDouble(centimeter_textview.getText().toString()) / 100;
                } else if (centimeter_Result.getText().toString().equals("Meter")) {
                    heightTemp = Double.parseDouble(centimeter_textview.getText().toString());
                } else if (centimeter_Result.getText().toString().equals("Feet")) {
                    heightTemp = Double.parseDouble(centimeter_textview.getText().toString()) / 0.3048;
                } else if (centimeter_Result.getText().toString().equals("Inch")) {
                    heightTemp = Double.parseDouble(centimeter_textview.getText().toString()) / 0.0254;
                }
                double final_result = (weighTemp / (heightTemp * heightTemp));
                if (final_result < 16.0 || final_result > 40.0) {
                    Toast.makeText(getApplicationContext(), "Invalid BMI! Please check your input", Toast.LENGTH_LONG).show();

                } else {
                    display_ResultTextView.setText(Double.toString(final_result));

                    if (Double.parseDouble(display_ResultTextView.getText().toString()) > 18.5 && Double.parseDouble(display_ResultTextView.getText().toString()) < 25)
                        normal_under_overWeight.setText("Normal");
                    else if (Double.parseDouble(display_ResultTextView.getText().toString()) < 18.5)
                        normal_under_overWeight.setText("OweUnderweight");
                    else if (Double.parseDouble(display_ResultTextView.getText().toString()) > 25.0)
                        normal_under_overWeight.setText("Overweight");
                    gridLayoutnew.setVisibility(view.GONE);
                    v.setVisibility(view.VISIBLE);

                }
            }
        });

        //for data deletion----------
        Button allClear = (Button) findViewById(R.id.AC);
        allClear.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if(flagOfWeight==1) {
                    weight_TextField.setText("0");
                }
                if(flagOfHeight==1)
                height_TextField.setText("0");
            }
        });
//for inserting the values--------------
      for(int i=0;i<10;i++) {
          final int finalI = i;
          list.get(i).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  

                  if(weight_TextField.getText().toString().equals("0") && flagOfWeight==1){
                      weight_TextField.setText("");
                  }
                  if(height_TextField.getText().toString().equals("0")&& flagOfHeight==1){
                      height_TextField.setText("");
                  }
                  if(flagOfWeight==1){
                  if (weight_TextField.getText().toString().equals("60")) {
                      weight_TextField.setText(numberButton[finalI]);
                  }else {
                      if(weight_Number.getText().toString().length()<3)

                          weight_TextField.append(numberButton[finalI]);
                  }
                  }else if(flagOfHeight==1){
                      if (height_TextField.getText().toString().equals("170")) {
                          height_TextField.setText(numberButton[finalI]);
                      } else {
                          if(height_TextField.getText().toString().length()<3)
                          height_TextField.append(numberButton[finalI]);

                      }
                  }
              }
          });
          oneText_clear.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  if(weight_Number.getText().toString().length()==1){
                      weight_Number.setText("0");
                  }
                  if(height_TextField.getText().toString().length()==1){
                      height_TextField.setText("0");
                  }

                  if(Double.parseDouble(weight_Number.getText().toString())!=0 && flagOfWeight==1) {
                  weight_Number.setText(weight_Number.getText().toString().substring(0,weight_Number.getText().toString().length()-1));

                  }
                  else if(Double.parseDouble(height_TextField.getText().toString())!=0 && flagOfHeight==1)
                  {
                      height_TextField.setText(height_TextField.getText().toString().substring(0,height_TextField.getText().toString().length()-1));

                  }
              }
          });

      }
      }
    }