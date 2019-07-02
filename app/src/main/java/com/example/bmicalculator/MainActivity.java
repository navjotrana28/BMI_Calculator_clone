package com.example.bmicalculator;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    public static final String KILOGRAM_KEY = "Kilogram";
    public static final String POUND_KEY = "Pound";
    public static final String CENTIMETER_KEY = "Centimeter";
    public static final String METER_KEY = "Meter";
    public static final String FEET_KEY = "Feet";
    public static final String INCH_KEY = "Inch";
    public static final String INVALID_BMI_PLEASE_CHECK_YOUR_INPUT = "Invalid BMI! Please check your input";
    public static final String NORMAL_KEY = "Normal";
    public static final String UNDERWEIGHT_KEY = "Underweight";
    public static final String OVERWEIGHT_KEY = "Overweight";
    public static final String ZERO_KEY = "0";
    public static final String SIXTY_KEY = "60";
    public static final String ONE_SEVENTY_KEY = "170";
    public static final String DECIMAL_KEY = ".";
    private static final String WEIGHT_VALUE = "weightvalue";
    private static final String RESULT_VISIBILITY = "result_visibility";
    public final String HEIGHT_VALUE = "HeightValue";
    public final String WEIGHT_UNITS = "weight_units_value";
    public final String HEIGHT_UNITS = "height_units_value";
    public final String DISPLAY_RESULT_VALUE = "display_result_value";
    public final String NORMAL_UNDER_OVERWEIGHT_VALUE = "normal_under_overweight";
    int flagOfWeight = 1, flagOfHeight = 0;
    int dotFlagWeight = 0, dotFlagHeight = 0;
    int flagOfnewInput = 0, flagOfnewInput2 = 0;
    boolean colorChange = false;

    ConstraintLayout weight_Left_LayoutText;
    ConstraintLayout height_Left_Layout;
    GridLayout gridLayout;
    TextView weightInput;
    TextView heightInput;
    TextView decimalDot;
    View includeResult;
    TextView display_ResultTextView;
    TextView normal_under_overWeight;
    TextView heightUnits;
    TextView weightUnits;
    ImageButton oneTextDelete;
    ConstraintLayout weightInputLayout;
    ConstraintLayout heightInputLayout;
    TextView textAllClear;
    Button goButton;

    public MainActivity() {
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weight_Left_LayoutText = findViewById(R.id.weight_Left_Layout);
        height_Left_Layout = findViewById(R.id.height_left_Layout);
        gridLayout = findViewById(R.id.grid_layout);
        weightInput = findViewById(R.id.kilogram_textview);
        heightInput = findViewById(R.id.centimeter_textview);
        decimalDot = findViewById(R.id.dot);
        includeResult = findViewById(R.id.include);
        //result layout initialization--------
        display_ResultTextView = findViewById(R.id.display_result);
        normal_under_overWeight = findViewById(R.id.normal);
        heightUnits = findViewById(R.id.centimeter_Syntax);
        weightUnits = findViewById(R.id.kilogram_Syntax);
        oneTextDelete = findViewById((R.id.hash));
        weightInputLayout = findViewById(R.id.weight_measure_layout);
        heightInputLayout = findViewById(R.id.height_measure_layout);
        textAllClear = findViewById(R.id.AC);
        goButton = findViewById(R.id.go);


        if (savedInstanceState != null) {
            weightInput.setText(savedInstanceState.getString(WEIGHT_VALUE));
            heightInput.setText(savedInstanceState.getString(HEIGHT_VALUE));
            if (savedInstanceState.getBoolean(RESULT_VISIBILITY)) {
                gridLayout.setVisibility(View.GONE);
                includeResult.setVisibility(View.VISIBLE);
            } else {
                gridLayout.setVisibility(View.VISIBLE);
                includeResult.setVisibility(View.GONE);
            }
            weightUnits.setText(savedInstanceState.getString(HEIGHT_UNITS));
            heightUnits.setText(savedInstanceState.getString(HEIGHT_UNITS));
            display_ResultTextView.setText(savedInstanceState.getString(DISPLAY_RESULT_VALUE));
            normal_under_overWeight.setText(savedInstanceState.getString(NORMAL_UNDER_OVERWEIGHT_VALUE));
        }

        final String[] numberButton = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        final Spinner heightSpinner;
        ArrayAdapter adapter2;
        final Spinner weightSpinner;
        heightSpinner = findViewById(R.id.spinner2);
        weightSpinner = findViewById(R.id.spinner1);
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

        weightInputLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weightInput.setTextColor(Color.RED);
                heightInput.setTextColor(getResources().getColor(R.color.colorDark_grey2));
                gridLayout.setVisibility(View.VISIBLE);
                findViewById(R.id.include).setVisibility(View.GONE);
                flagOfWeight = 1;
                flagOfHeight = 0;
                flagOfnewInput = 1;
                flagOfnewInput2 = 0;
            }
        });

        heightInputLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heightInput.setTextColor(Color.RED);
                weightInput.setTextColor(getResources().getColor(R.color.colorDark_grey2));
                gridLayout.setVisibility(View.VISIBLE);
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
        final String[] weightTypeList = {KILOGRAM_KEY, POUND_KEY};
        final String[] heightTypeList = {CENTIMETER_KEY, METER_KEY, FEET_KEY, INCH_KEY};

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, weightTypeList);
        weightSpinner.setAdapter(adapter);
        weightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                weightUnits.setText(weightTypeList[i]);
                weightInput.setTextColor(getResources().getColor(R.color.colorDarkorange));
                flagOfWeight = 1;
                heightInput.setTextColor((getResources().getColor(R.color.colorDark_grey2)));
                flagOfHeight = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, heightTypeList);
        heightSpinner.setAdapter(adapter2);
        heightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                heightUnits.setText(heightTypeList[i]);
                if (colorChange) {
                    heightInput.setTextColor(getResources().getColor(R.color.colorRED));
                    flagOfWeight = 0;
                    weightInput.setTextColor(getResources().getColor(R.color.colorDark_grey2));
                    flagOfHeight = 1;
                } else {
                    colorChange = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        weight_Left_LayoutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weightSpinner.performClick();
            }
        });
        height_Left_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heightSpinner.performClick();
            }
        });

//for inserting the values--------------
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            list.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (weightInput.getText().toString().equals(ZERO_KEY) && flagOfWeight == 1) {
                        weightInput.setText("");
                    }
                    if (heightInput.getText().toString().equals(ZERO_KEY) && flagOfHeight == 1) {
                        heightInput.setText("");
                    }
                    if (flagOfWeight == 1) {
                        if (weightInput.getText().toString().equals(SIXTY_KEY) || flagOfnewInput == 1) {
                            weightInput.setText(numberButton[finalI]);
                            flagOfnewInput = 0;
                        } else if (weightInput.getText().toString().length() < 6) {
                            if (weightInput.getText().toString().contains(DECIMAL_KEY)) {
                                if (weightInput.getText().toString().length() - weightInput.getText().toString().indexOf(DECIMAL_KEY) < 3) {
                                    weightInput.append(numberButton[finalI]);
                                }
                            } else if (weightInput.getText().toString().length() < 3) {
                                weightInput.append(numberButton[finalI]);
                            }
                        }
                    } else if (flagOfHeight == 1) {
                        if (heightInput.getText().toString().equals(ONE_SEVENTY_KEY) || flagOfnewInput2 == 1) {
                            heightInput.setText(numberButton[finalI]);
                            flagOfnewInput2 = 0;
                        } else if (heightInput.getText().toString().length() < 6) {
                            if (heightInput.getText().toString().contains(DECIMAL_KEY)) {
                                if (heightInput.getText().toString().length() - heightInput.getText().toString().indexOf(".") < 3) {
                                    heightInput.append(numberButton[finalI]);
                                }
                            } else if (heightInput.getText().toString().length() < 3) {
                                heightInput.append(numberButton[finalI]);
                            }
                        }

                    }
                }
            });
        }
        //GO button for result-----------
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double weighTemp = 0, heightTemp = 0;
                if (weightUnits.getText().toString().equals(KILOGRAM_KEY)) {
                    weighTemp = Double.parseDouble(weightInput.getText().toString());
                } else if (weightUnits.getText().toString().equals(POUND_KEY)) {
                    weighTemp = Double.parseDouble(weightInput.getText().toString()) / 0.453592;
                }
                if (heightUnits.getText().toString().equals(CENTIMETER_KEY)) {
                    heightTemp = Double.parseDouble(heightInput.getText().toString()) / 100;
                } else if (heightUnits.getText().toString().equals(METER_KEY)) {
                    heightTemp = Double.parseDouble(heightInput.getText().toString());
                } else if (heightUnits.getText().toString().equals(FEET_KEY)) {
                    heightTemp = Double.parseDouble(heightInput.getText().toString()) / 3.28084;
                } else if (heightUnits.getText().toString().equals(INCH_KEY)) {
                    heightTemp = Double.parseDouble(heightInput.getText().toString()) / 39.3701;
                }
                double final_result = (weighTemp / (heightTemp * heightTemp));
                Math.round(final_result);
                if (final_result < 16.0 || final_result > 50.0) {
                    Toast.makeText(getApplicationContext(), INVALID_BMI_PLEASE_CHECK_YOUR_INPUT, Toast.LENGTH_LONG).show();

                } else if (weighTemp == 0 && heightTemp == 0) {
                    Toast.makeText(getApplicationContext(), INVALID_BMI_PLEASE_CHECK_YOUR_INPUT, Toast.LENGTH_LONG).show();
                } else {
                    display_ResultTextView.setText(Double.toString(final_result));

                    if (Double.parseDouble(display_ResultTextView.getText().toString()) > 18.5 && Double.parseDouble(display_ResultTextView.getText().toString()) < 25) {
                        normal_under_overWeight.setText(NORMAL_KEY);
                        normal_under_overWeight.setTextColor(getResources().getColor(R.color.colorGreen));
                    } else if (Double.parseDouble(display_ResultTextView.getText().toString()) < 18.5) {
                        normal_under_overWeight.setText(UNDERWEIGHT_KEY);
                        normal_under_overWeight.setTextColor(getResources().getColor(R.color.colorBlue));
                    } else if (Double.parseDouble(display_ResultTextView.getText().toString()) > 25.0) {
                        normal_under_overWeight.setText(OVERWEIGHT_KEY);
                        normal_under_overWeight.setTextColor(getResources().getColor(R.color.colorDarkorange));
                    }
                    gridLayout.setVisibility(View.GONE);
                    includeResult.setVisibility(View.VISIBLE);

                }
            }
        });

        oneTextDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (weightInput.getText().toString().length() == 1) {
                    weightInput.setText(ZERO_KEY);
                }
                if (heightInput.getText().toString().length() == 1) {
                    heightInput.setText(ZERO_KEY);
                }

                if (Double.parseDouble(weightInput.getText().toString()) != 0 && flagOfWeight == 1) {
                    weightInput.setText(weightInput.getText().toString().substring(0, weightInput.getText().toString().length() - 1));

                } else if (Double.parseDouble(heightInput.getText().toString()) != 0 && flagOfHeight == 1) {
                    heightInput.setText(heightInput.getText().toString().substring(0, heightInput.getText().toString().length() - 1));

                }
            }
        });
        //for data deletion----------
        textAllClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flagOfWeight == 1) {
                    weightInput.setText(ZERO_KEY);
                }
                if (flagOfHeight == 1)
                    heightInput.setText(ZERO_KEY);
                dotFlagWeight = 0;
                dotFlagHeight = 0;
            }
        });

        decimalDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flagOfWeight == 1) {
                    if (!weightInput.getText().toString().contains(DECIMAL_KEY)) {
                        weightInput.append(decimalDot.getText());
                        dotFlagWeight = 1;
                    }
                } else if (flagOfHeight == 1) {
                    if (!heightInput.getText().toString().contains(DECIMAL_KEY)) {
                        heightInput.append(decimalDot.getText());
                        dotFlagHeight = 1;
                    }
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //  outState.putString(HEIGHT_VALUE_KEY,);
        outState.putString(WEIGHT_VALUE, weightInput.getText().toString());
        outState.putString(HEIGHT_VALUE, heightInput.getText().toString());
        outState.putBoolean(RESULT_VISIBILITY, false);
        Log.d("emre1s", includeResult.getVisibility() + " ?");
        if (includeResult.getVisibility() == View.VISIBLE) {
            outState.putBoolean(RESULT_VISIBILITY, true);
        }
        outState.putString(WEIGHT_UNITS, weightUnits.getText().toString());
        outState.putString(HEIGHT_UNITS, heightUnits.getText().toString());
        outState.putString(DISPLAY_RESULT_VALUE, display_ResultTextView.getText().toString());
        outState.putString(NORMAL_UNDER_OVERWEIGHT_VALUE, normal_under_overWeight.getText().toString());


    }

}