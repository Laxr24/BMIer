package com.example.resincalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.resincalculator.R.id;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView result;
        result = findViewById(id.displayResult);
        EditText height = findViewById(R.id.height_input);
        EditText weight = findViewById(R.id.weight_input);
        EditText age = findViewById(R.id.age_input);
        Button calculate = findViewById(R.id.calculateButton);
        Button reset = findViewById(R.id.resetButton);

//        Time to run the click function !



        calculate.setOnClickListener(v -> {

//                    Check if every field is correctly filled with values then run codes.
            if(!height.getText().toString().isEmpty() && !weight.getText().toString().isEmpty() && !age.getText().toString().isEmpty()){
                Double num_height = ((Float.parseFloat(height.getText().toString()) * 12 ) * 2.53 )/100 ;
                Float num_weight = Float.parseFloat(weight.getText().toString());
//                Integer num_age = Integer.parseInt(age.getText().toString());
                calculateBMI(num_weight, num_height, result);
            }
            else {
                Toast.makeText(getApplicationContext(), "Please fill all the fields!", Toast.LENGTH_SHORT).show();
            }


        });

//        Reseting all data

        reset.setOnClickListener(v -> {
            resetAllData(result, height, age, weight);
            Log.d("cusTomMsg", "All data was reset !");
        });


    }

    @SuppressLint("SetTextI18n")
    static void resetAllData(TextView result, EditText height, EditText age, EditText weight){
        result.setText("Enter data to get BMI");
        height.setText("");
        age.setText("");
        weight.setText("");
    }
    private static void calculateBMI(Float weight, Double height, TextView textContainer){
        Double  bmi = weight /(height*height);

        if(bmi > 25){
            Log.d("cusTomMsg", "You are Overweight");
            textContainer.setText("You are Overweight 💀 \n BMI is: " + Math.floor(bmi));
        } else if (bmi <= 18.5) {
            Log.d("cusTomMsg", "You are Underweight");
            textContainer.setText("You are Underweight 👍\n BMI is: " + Math.floor(bmi));
        } else if (bmi <= 24.9 && bmi >= 18.5) {
            Log.d("cusTomMsg", "You are quite healthy");
            textContainer.setText("You are quite healthy ♥\n BMI is: " + Math.floor(bmi));
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder exitDialog = new AlertDialog.Builder(this);
        exitDialog.setTitle("Close application? ❌");
        exitDialog.setMessage("close application and exit")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

//            Show dialog
                exitDialog.show();

    }
}