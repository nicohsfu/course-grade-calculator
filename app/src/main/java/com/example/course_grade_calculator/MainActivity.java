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

    SeekBar examSeekBar;
    double examSeekBarValue;
    TextView examSeekBarTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignmentsEditText = findViewById(R.id.assignmentsEditText);
        assignmentsEditText.addTextChangedListener(this);

        examSeekBar = findViewById(R.id.examSeekBar);
        examSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        examSeekBarTextView = findViewById(R.id.examSeekBarTextView);
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            examSeekBarValue = seekBar.getProgress();
            examSeekBarTextView.setText(String.valueOf(examSeekBarValue));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

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
