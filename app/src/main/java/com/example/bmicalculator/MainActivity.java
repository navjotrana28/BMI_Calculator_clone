package com.example.bmicalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public static final String KILOGRAM = "Kilogram";
    public static final String POUND = "Pound";
    public static final String CENTIMETER = "Centimeter";
    public static final String METER = "Meter";
    public static final String FEET = "Feet";
    public static final String INCH = "Inch";
    public static final String INVALID_BMI_PLEASE_CHECK_YOUR_INPUT = "Invalid BMI! Please check your input";
    public static final String NORMAL = "Normal";
    public static final String UNDERWEIGHT = "Underweight";
    public static final String OVERWEIGHT = "Overweight";
    public static final String ZERO = "0";
    public static final String SIXTY = "60";
    public static final String ONE_SEVENTY = "170";
    public static final String DECIMAL = ".";


    int flagOfWeight = 1, flagOfHeight = 0;
    int dotFlagWeight = 0, dotFlagHeight = 0;
    int flagOfnewInput = 0, flagOfnewInput2 = 0;
    boolean colorChange = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] numberButton = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        final Spinner spin2;
        ArrayAdapter adapter2;
        final Spinner sp;
        Button goButton = findViewById(R.id.go);
        final LinearLayout linearResult = findViewById(R.id.result_layout);
        final View v = findViewById(R.id.include);
        final GridLayout gridLayoutnew = findViewById(R.id.grid_layout);
        spin2 = findViewById(R.id.spinner2);
        sp = findViewById(R.id.spinner1);
        final ArrayList<Button> list = new ArrayList<>();
        final Button zero = findViewById(R.id.zero);
        list.add(zero);
        final Button one = findViewById(R.id.one);
        list.add(one);
        final Button two = findViewById(R.id.two);
        list.add(two);
        final Button three = findViewById(R.id.three);
        list.add(three);
        final Button four = findViewById(R.id.four);
        list.add(four);
        final Button five = findViewById(R.id.five);
        list.add(five);
        final Button six = findViewById(R.id.six);
        list.add(six);
        final Button seven = findViewById(R.id.seven);
        list.add(seven);
        final Button eight = findViewById(R.id.eight);
        list.add(eight);
        final Button nine = findViewById(R.id.nine);
        list.add(nine);
//for change of color of weight and height..

        ConstraintLayout weight_measure_layout = findViewById(R.id.weight_measure_layout);
        ConstraintLayout height_measure_layout = findViewById(R.id.height_measure_layout);
        final ConstraintLayout weight_left_layout = findViewById(R.id.weight_Left_Layout);
        final ConstraintLayout height_left_layout = findViewById(R.id.height_left_Layout);
        final TextView weight_Number = findViewById(R.id.kilogram_textview);
        final TextView height_TextField = findViewById(R.id.centimeter_textview);
        final TextView dot = findViewById(R.id.dot);

        //result layout initialization--------
        final TextView display_ResultTextView = findViewById(R.id.display_result);
        final TextView normal_under_overWeight = findViewById(R.id.normal);
        final TextView centimeter_Result = findViewById(R.id.centimeter_Syntax);
        final TextView kilogram_Result = findViewById(R.id.kilogram_Syntax);
        final ImageButton oneText_clear = (ImageButton)findViewById((R.id.hash));


        weight_measure_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight_Number.setTextColor(Color.RED);
                height_TextField.setTextColor(getResources().getColor(R.color.colorDark_grey2));
                gridLayoutnew.setVisibility(View.VISIBLE);
                findViewById(R.id.include).setVisibility(View.GONE);
                flagOfWeight = 1;
                flagOfHeight = 0;
                flagOfnewInput = 1;
                flagOfnewInput2 = 0;
            }
        });
        height_measure_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                height_TextField.setTextColor(Color.RED);
                weight_Number.setTextColor(getResources().getColor(R.color.colorDark_grey2));
                gridLayoutnew.setVisibility(View.VISIBLE);
                findViewById(R.id.include).setVisibility(View.GONE);
                flagOfWeight = 0;
                flagOfHeight = 1;
                flagOfnewInput = 0;
                flagOfnewInput2 = 1;
            }
        });


        for (int i = 0; i < 10; i++) {

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
        final String[] weightlist = {KILOGRAM, POUND};
        final String[] heightlist = {CENTIMETER, METER,FEET, INCH};

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, weightlist);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kilogram_Result.setText(weightlist[i]);
                weight_Number.setTextColor(getResources().getColor(R.color.colorDarkorange));
                flagOfWeight = 1;
                height_TextField.setTextColor((getResources().getColor(R.color.colorDark_grey2)));
                flagOfHeight = 0;
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
                centimeter_Result.setText(heightlist[i]);
                if (colorChange) {
                    height_TextField.setTextColor(getResources().getColor(R.color.colorRED));
                    flagOfWeight = 0;
                    weight_Number.setTextColor(getResources().getColor(R.color.colorDark_grey2));
                    flagOfHeight = 1;
                } else {
                    colorChange = true;
                }
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
                if (kilogram_Result.getText().toString().equals(KILOGRAM)) {
                    weighTemp = Double.parseDouble(weight_Number.getText().toString());
                } else if (kilogram_Result.getText().toString().equals(POUND)) {
                    weighTemp = Double.parseDouble(weight_Number.getText().toString()) / 0.4535;
                }
                if (centimeter_Result.getText().toString().equals(CENTIMETER)) {
                    heightTemp = Double.parseDouble(height_TextField.getText().toString()) / 100;
                } else if (centimeter_Result.getText().toString().equals(METER)) {
                    heightTemp = Double.parseDouble(height_TextField.getText().toString());
                } else if (centimeter_Result.getText().toString().equals(FEET)) {
                    heightTemp = Double.parseDouble(height_TextField.getText().toString()) / 3.281;
                } else if (centimeter_Result.getText().toString().equals(INCH)) {
                    heightTemp = Double.parseDouble(height_TextField.getText().toString()) / 39.37;
                }
                double final_result = (weighTemp / (heightTemp * heightTemp));
                if (final_result < 16.0 || final_result > 50.0) {
                    Toast.makeText(getApplicationContext(), INVALID_BMI_PLEASE_CHECK_YOUR_INPUT, Toast.LENGTH_LONG).show();

                } else if (weighTemp == 0 && heightTemp == 0) {
                    Toast.makeText(getApplicationContext(), INVALID_BMI_PLEASE_CHECK_YOUR_INPUT, Toast.LENGTH_LONG).show();
                } else {
                    display_ResultTextView.setText(Double.toString(final_result));

                    if (Double.parseDouble(display_ResultTextView.getText().toString()) > 18.5 && Double.parseDouble(display_ResultTextView.getText().toString()) < 25)
                        normal_under_overWeight.setText(NORMAL);
                    else if (Double.parseDouble(display_ResultTextView.getText().toString()) < 18.5)
                        normal_under_overWeight.setText(UNDERWEIGHT);
                    else if (Double.parseDouble(display_ResultTextView.getText().toString()) > 25.0)
                        normal_under_overWeight.setText(OVERWEIGHT);
                    gridLayoutnew.setVisibility(View.GONE);
                    v.setVisibility(View.VISIBLE);

                }
            }
        });

        //for data deletion----------
        Button allClear = findViewById(R.id.AC);
        allClear.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if (flagOfWeight == 1) {
                    weight_Number.setText(ZERO);
                }
                if (flagOfHeight == 1)
                    height_TextField.setText(ZERO);
                dotFlagWeight = 0;
                dotFlagHeight = 0;
            }
        });
//for inserting the values--------------
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            list.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (weight_Number.getText().toString().equals(ZERO) && flagOfWeight == 1) {
                        weight_Number.setText("");

                    }
                    if (height_TextField.getText().toString().equals(ZERO) && flagOfHeight == 1) {
                        height_TextField.setText("");
                    }
                    if (flagOfWeight == 1) {
                        if (weight_Number.getText().toString().equals(SIXTY) || flagOfnewInput == 1) {
                            weight_Number.setText(numberButton[finalI]);
                            flagOfnewInput = 0;
                        } else if (weight_Number.getText().toString().length() < 6) {
                            if (weight_Number.getText().toString().contains(DECIMAL)) {
                                if (weight_Number.getText().toString().length() - weight_Number.getText().toString().indexOf(DECIMAL) < 3) {
                                    weight_Number.append(numberButton[finalI]);
                                }
                            } else if (weight_Number.getText().toString().length() < 3) {
                                weight_Number.append(numberButton[finalI]);
                            }
                        }

                    } else if (flagOfHeight == 1) {
                        if (height_TextField.getText().toString().equals(ONE_SEVENTY) || flagOfnewInput2 == 1) {
                            height_TextField.setText(numberButton[finalI]);
                            flagOfnewInput2 = 0;
                        } else if (height_TextField.getText().toString().length() < 6) {
                            if (height_TextField.getText().toString().contains(DECIMAL)) {
                                if (height_TextField.getText().toString().length() - height_TextField.getText().toString().indexOf(".") < 3) {
                                    height_TextField.append(numberButton[finalI]);
                                }
                            } else if (height_TextField.getText().toString().length() < 3) {
                                height_TextField.append(numberButton[finalI]);
                            }
                        }

                    }
                }
            });
            oneText_clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (weight_Number.getText().toString().length() == 1) {
                        weight_Number.setText(ZERO);
                    }
                    if (height_TextField.getText().toString().length() == 1) {
                        height_TextField.setText(ZERO);
                    }

                    if (Double.parseDouble(weight_Number.getText().toString()) != 0 && flagOfWeight == 1) {
                        weight_Number.setText(weight_Number.getText().toString().substring(0, weight_Number.getText().toString().length() - 1));

                    } else if (Double.parseDouble(height_TextField.getText().toString()) != 0 && flagOfHeight == 1) {
                        height_TextField.setText(height_TextField.getText().toString().substring(0, height_TextField.getText().toString().length() - 1));

                    }

                }
            });
            dot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (flagOfWeight == 1) {
                        if (!weight_Number.getText().toString().contains(DECIMAL)) {
                            weight_Number.append(dot.getText());
                            dotFlagWeight = 1;
                        }
                    } else if (flagOfHeight == 1) {
                        if (!height_TextField.getText().toString().contains(DECIMAL)) {
                            height_TextField.append(dot.getText());
                            dotFlagHeight = 1;
                        }
                    }
                }
            });

        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}