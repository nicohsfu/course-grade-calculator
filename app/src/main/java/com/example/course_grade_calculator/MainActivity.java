package com.example.course_grade_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    EditText assignmentsEditText;

    double assignmentsGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignmentsEditText = findViewById(R.id.assignmentsEditText);
        assignmentsEditText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (assignmentsEditText.isFocused()) {
            try {
                assignmentsGrade = Double.parseDouble(assignmentsEditText.getText().toString());
            } catch (NumberFormatException e) {
                assignmentsGrade = 0.0;
            }
            Toast.makeText(this, String.valueOf(assignmentsGrade), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
