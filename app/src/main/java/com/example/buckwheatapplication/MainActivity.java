package com.example.buckwheatapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText adults;
    private EditText children;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private EditText result;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adults = findViewById(R.id.adults);
        children = findViewById(R.id.children);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        result = findViewById(R.id.result);
        button = findViewById(R.id.main_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // adult 2500 child 2000 1 kg buckwheat 1050 cal
                // lose weight -20% , gain weight +15%, hold 0%
                int caloriesAdultNeed = 2500;
                int caloriesChildNeed = 2000;
                int caloriesPerKgOfBuckwheat = 1050;
                int percentsForGain = 15;
                int percentsForLose = 20;
                try {
                    int ad = Integer.parseInt(String.valueOf(adults.getText()));
                    int ch = Integer.parseInt(String.valueOf(children.getText()));
                    if (radioButton1.isChecked()) {
                        result.setText("You need: " + ((ad * (caloriesAdultNeed - ((caloriesAdultNeed / 100) * percentsForLose)) + ch * (caloriesChildNeed - ((caloriesChildNeed / 100) * percentsForLose))) / caloriesPerKgOfBuckwheat) + " kg of buckwheat");
                        return;
                    } else if (radioButton3.isChecked()) {
                        result.setText("You need: " + ((ad * (caloriesAdultNeed + ((caloriesAdultNeed / 100) * percentsForGain)) + ch * (caloriesChildNeed + ((caloriesChildNeed / 100) * percentsForGain))) / caloriesPerKgOfBuckwheat) + " kg of buckwheat");
                        return;
                    } else if (radioButton2.isChecked()) {
                        result.setText("You need: " + ((ad * caloriesAdultNeed + ch * caloriesChildNeed) / caloriesPerKgOfBuckwheat) + " kg of buckwheat");
                        return;
                    } else result.setText("Choose best option for you");
                } catch (Exception e) {
                    result.setText("Invalid input");
                }

            }
        });
    }
}