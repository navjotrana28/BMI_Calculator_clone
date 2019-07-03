package com.example.bmicalculator;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
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

import static com.example.bmicalculator.R.color;
import static com.example.bmicalculator.R.id;
import static com.example.bmicalculator.R.layout;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final double LOWER_BOUND_UNDER_WEIGHT = 16.0;
    public static final double OUT_OF_BOUND_OVERWEIGHT = 50.0;
    public static final double NORMAL_RANGE = 18.5;
    public static final double LOWER_BOUND_NORMAL = NORMAL_RANGE;
    public static final double OUTER_BOUND_NORMAL = 25.0;
    public static final int CENTIMETER_TO_METER = 100;
    public static final double FEET_TO_METER = 3.28084;
    public static final double INCH_TO_METRE = 39.3701;
    public static final double INCH_TO_METER = INCH_TO_METRE;
    private static final String KILOGRAM_KEY = "Kilogram";
    private static final String POUND_KEY = "Pound";
    private static final String CENTIMETER_KEY = "Centimeter";
    private static final String METER_KEY = "Meter";
    private static final String FEET_KEY = "Feet";
    private static final String INCH_KEY = "Inch";
    private static final String INVALID_BMI_PLEASE_CHECK_YOUR_INPUT = "Invalid BMI! Please check your input";
    private static final String UNDERWEIGHT_KEY = "Underweight";
    private static final String OVERWEIGHT_KEY = "Overweight";
    private static final String ZERO_KEY = "0";
    private static final String SIXTY_KEY = "60";
    private static final String ONE_SEVENTY_KEY = "170";
    private static final String DECIMAL_KEY = ".";
    private static final String WEIGHT_VALUE = "weightvalue";
    private static final String RESULT_VISIBILITY = "result_visibility";
    private final String HEIGHT_VALUE = "HeightValue";
    private final String WEIGHT_UNITS = "weight_units_value";
    private final String HEIGHT_UNITS = "height_units_value";
    private final String DISPLAY_RESULT_VALUE = "display_result_value";
    private final String NORMAL_UNDER_OVERWEIGHT_VALUE = "normal_under_overweight";
    Boolean dotFlagWeight = false, dotFlagHeight = false;
    ConstraintLayout weight_Left_LayoutText;
    ConstraintLayout height_Left_Layout;
    GridLayout gridLayout;
    TextView weightInput;
    TextView heightInput;
    TextView decimalDot;
    View includeResultLayout;
    TextView display_ResultTextView;
    TextView normal_under_overWeight;
    TextView heightUnits;
    TextView weightUnits;
    ImageButton oneTextDelete;
    ConstraintLayout weightInputLayout;
    ConstraintLayout heightInputLayout;
    TextView textAllClear;
    Button goButton;
    //TODO give access modifier
    private Boolean flagOfWeight = true, flagOfHeight = false;
    private Boolean reinitializeWeight = false, reinitializeHeight = false;
    private boolean colorChange = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        weight_Left_LayoutText = findViewById(id.weight_Left_Layout);
        height_Left_Layout = findViewById(id.height_left_Layout);
        gridLayout = findViewById(id.grid_layout);
        weightInput = findViewById(id.weight_InputTextView);
        heightInput = findViewById(id.height_InputTextView);
        decimalDot = findViewById(id.dot);
        includeResultLayout = findViewById(id.include);
        display_ResultTextView = findViewById(id.display_result);
        normal_under_overWeight = findViewById(id.normal);
        heightUnits = findViewById(id.syntax_Syntax);
        weightUnits = findViewById(id.weight_Syntax);
        oneTextDelete = findViewById((id.hash));
        weightInputLayout = findViewById(id.weight_Input_layout);
        heightInputLayout = findViewById(id.height_Input_layout);
        textAllClear = findViewById(id.AC);
        goButton = findViewById(id.go);

        weightInputLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weightInput.setTextColor(Color.RED);
                heightInput.setTextColor(getResources().getColor(color.colorDark_grey2));
                gridLayout.setVisibility(View.VISIBLE);
                findViewById(id.include).setVisibility(View.GONE);
                flagOfWeight = reinitializeWeight = true;
                reinitializeHeight = flagOfHeight = false;
            }
        });

        heightInputLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heightInput.setTextColor(Color.RED);
                weightInput.setTextColor(getResources().getColor(color.colorDark_grey2));
                gridLayout.setVisibility(View.VISIBLE);
                findViewById(id.include).setVisibility(View.GONE);
                flagOfWeight = reinitializeWeight = false;
                flagOfHeight = reinitializeHeight = true;
            }
        });


        final String[] weightTypeList = {KILOGRAM_KEY, POUND_KEY};
        final Spinner weightSpinner = findViewById(id.spinner1);
        ArrayAdapter weightAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, weightTypeList);
        weightSpinner.setAdapter(weightAdapter);
        weightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                weightUnits.setText(weightTypeList[i]);
                weightInput.setTextColor(getResources().getColor(color.colorDarkorange));
                flagOfWeight = true;
                heightInput.setTextColor((getResources().getColor(color.colorDark_grey2)));
                flagOfHeight = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        final String[] heightTypeList = {CENTIMETER_KEY, METER_KEY, FEET_KEY, INCH_KEY};
        final Spinner heightSpinner = findViewById(id.heightSpinner);
        ArrayAdapter heightAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, heightTypeList);
        heightSpinner.setAdapter(heightAdapter);
        heightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                heightUnits.setText(heightTypeList[i]);
                if (colorChange) {
                    heightInput.setTextColor(getResources().getColor(color.colorRED));
                    flagOfWeight = false;
                    weightInput.setTextColor(getResources().getColor(color.colorDark_grey2));
                    flagOfHeight = true;
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

    }

    private void methodcreated(double bmi) {

        if (bmi < LOWER_BOUND_NORMAL) {
            normal_under_overWeight.setText(UNDERWEIGHT_KEY);
            normal_under_overWeight.setTextColor(getResources().getColor(color.colorBlue));
        } else if (bmi > OUTER_BOUND_NORMAL) {
            normal_under_overWeight.setText(OVERWEIGHT_KEY);
            normal_under_overWeight.setTextColor(getResources().getColor(color.colorDarkorange));
        }
        gridLayout.setVisibility(View.GONE);
        includeResultLayout.setVisibility(View.VISIBLE);

    }


    private double getWeightInput(double weight, String unit) {
        switch (unit) {
            case KILOGRAM_KEY: {
                return weight;
            }
            case POUND_KEY: {
                return weight / 0.453592;
            }

        }
        return 0;
    }

    private double getHeightInput(double height, String unit) {
        switch (unit) {
            case CENTIMETER_KEY: {
                return height / CENTIMETER_TO_METER;
            }
            case METER_KEY: {
                return height;
            }
            case FEET_KEY: {
                return height / FEET_TO_METER;
            }
            case INCH_KEY: {
                return height / INCH_TO_METER;

            }
        }
        return 0;
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        weightInput.setText(savedInstanceState.getString(WEIGHT_VALUE));
        heightInput.setText(savedInstanceState.getString(HEIGHT_VALUE));
        if (savedInstanceState.getBoolean(RESULT_VISIBILITY)) {
            gridLayout.setVisibility(View.GONE);
            includeResultLayout.setVisibility(View.VISIBLE);
        } else {
            gridLayout.setVisibility(View.VISIBLE);
            includeResultLayout.setVisibility(View.GONE);
        }
        weightUnits.setText(savedInstanceState.getString(WEIGHT_UNITS));
        heightUnits.setText(savedInstanceState.getString(HEIGHT_UNITS));
        display_ResultTextView.setText(savedInstanceState.getString(DISPLAY_RESULT_VALUE));
        normal_under_overWeight.setText(savedInstanceState.getString(NORMAL_UNDER_OVERWEIGHT_VALUE));
    }

    @Override
    public void onClick(View view) {
        String weightTemperory = weightInput.getText().toString();
        String heighttemperory = heightInput.getText().toString();
        switch (view.getId()) {
            case R.id.zero:
            case R.id.one:
            case R.id.two:
            case R.id.three:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eight:
            case R.id.nine: {
                if (weightTemperory.equals(ZERO_KEY) && flagOfWeight) {
                    weightInput.setText("");
                }
                if (heighttemperory.equals(ZERO_KEY) && flagOfHeight) {
                    heightInput.setText("");
                }
                if (flagOfWeight) {
                    if (weightTemperory.equals(SIXTY_KEY) || reinitializeWeight) {
                        weightInput.setText(((TextView) view).getText().toString());
                        reinitializeWeight = false;
                    } else if (weightTemperory.length() < 6) {
                        if (weightTemperory.contains(DECIMAL_KEY)) {
                            if (weightTemperory.length() - weightTemperory.indexOf(DECIMAL_KEY) < 3) {
                                weightInput.append(((TextView) view).getText().toString());
                            }
                        } else if (weightTemperory.length() < 3) {
                            weightInput.append(((TextView) view).getText().toString());
                        }
                    }
                } else if (flagOfHeight) {
                    if (heighttemperory.equals(ONE_SEVENTY_KEY) || reinitializeHeight) {
                        heightInput.setText(((TextView) view).getText().toString());
                        reinitializeHeight = false;
                    } else if (heighttemperory.length() < 6) {
                        if (heighttemperory.contains(DECIMAL_KEY)) {
                            if (heighttemperory.length() - heighttemperory.indexOf(DECIMAL_KEY) < 3) {
                                heightInput.append(((TextView) view).getText().toString());
                            }
                        } else if (heighttemperory.length() < 3) {
                            heightInput.append(((TextView) view).getText().toString());
                        }
                    }
                }
                break;
            }
            case R.id.go: {
                double weight = getWeightInput(Double.parseDouble(weightTemperory), weightUnits.getText().toString());
                double height = getHeightInput(Double.parseDouble(heighttemperory), heightUnits.getText().toString());
                double bmi_Calcualte = weight / (height * height);
                Math.round(bmi_Calcualte);
                if (bmi_Calcualte < LOWER_BOUND_UNDER_WEIGHT || bmi_Calcualte > OUT_OF_BOUND_OVERWEIGHT) {
                    Toast.makeText(getApplicationContext(), INVALID_BMI_PLEASE_CHECK_YOUR_INPUT, Toast.LENGTH_LONG).show();
                } else if (weight == 0 && height == 0) {
                    Toast.makeText(getApplicationContext(), INVALID_BMI_PLEASE_CHECK_YOUR_INPUT, Toast.LENGTH_LONG).show();
                } else {
                    display_ResultTextView.setText(Double.toString(bmi_Calcualte));
                    double bmi = Double.parseDouble(display_ResultTextView.getText().toString());
                    methodcreated(bmi);
                }
                break;
            }
            case R.id.hash: {
                if (weightTemperory.length() == 1) {
                    weightInput.setText(ZERO_KEY);
                }
                if (heighttemperory.length() == 1) {
                    heightInput.setText(ZERO_KEY);
                }

                if (Double.parseDouble(weightTemperory) != 0 && flagOfWeight) {
                    weightInput.setText(weightTemperory.substring(0, weightTemperory.length() - 1));

                } else if (Double.parseDouble(heighttemperory) != 0 && flagOfHeight) {
                    heightInput.setText(heighttemperory.substring(0, heighttemperory.length() - 1));
                }
                break;
            }
            case R.id.AC: {
                if (flagOfWeight) {
                    weightInput.setText(ZERO_KEY);
                }
                if (flagOfHeight)
                    heightInput.setText(ZERO_KEY);
                dotFlagWeight = false;
                dotFlagHeight = false;
                break;
            }
            case R.id.dot: {
                if (flagOfWeight) {
                    if (!weightTemperory.contains(DECIMAL_KEY)) {
                        weightInput.append(decimalDot.getText());
                        dotFlagWeight = true;
                    }
                } else if (flagOfHeight) {
                    if (!heighttemperory.contains(DECIMAL_KEY)) {
                        heightInput.append(decimalDot.getText());
                        dotFlagHeight = true;
                    }
                }
                break;
            }
        }
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        //
        super.onSaveInstanceState(outState);
        outState.putString(WEIGHT_VALUE, weightInput.getText().toString());
        outState.putString(HEIGHT_VALUE, heightInput.getText().toString());
        outState.putString(WEIGHT_UNITS, weightUnits.getText().toString());
        outState.putString(HEIGHT_UNITS, heightUnits.getText().toString());
        outState.putString(DISPLAY_RESULT_VALUE, display_ResultTextView.getText().toString());
        outState.putString(NORMAL_UNDER_OVERWEIGHT_VALUE, normal_under_overWeight.getText().toString());
        outState.putBoolean(RESULT_VISIBILITY, false);
        if (includeResultLayout.getVisibility() == View.VISIBLE) {
            outState.putBoolean(RESULT_VISIBILITY, true);
        }

    }
}