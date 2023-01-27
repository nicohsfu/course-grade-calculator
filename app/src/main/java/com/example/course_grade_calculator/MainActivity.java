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
    EditText participationEditText;
    EditText projectEditText;
    EditText quizzesEditText;

    double assignmentsGrade;
    double participationGrade;
    double projectGrade;
    double quizzesGrade;

    SeekBar examSeekBar;
    double examSeekBarValue;
    TextView examSeekBarTextView;

    EditText finalMarkEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignmentsEditText = findViewById(R.id.assignmentsEditText);
        assignmentsEditText.addTextChangedListener(this);

        participationEditText = findViewById(R.id.participationEditText);
        participationEditText.addTextChangedListener(this);

        projectEditText = findViewById(R.id.projectEditText);
        projectEditText.addTextChangedListener(this);

        quizzesEditText = findViewById(R.id.quizzesEditText);
        quizzesEditText.addTextChangedListener(this);

        examSeekBar = findViewById(R.id.examSeekBar);
        examSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        examSeekBarTextView = findViewById(R.id.examSeekBarTextView);

        finalMarkEditText = findViewById(R.id.finalMarkEditText);
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            examSeekBarValue = seekBar.getProgress();
            examSeekBarTextView.setText(String.valueOf(examSeekBarValue));
            updateStandard();
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
        if (participationEditText.isFocused()) {
            try {
                participationGrade = Double.parseDouble(participationEditText.getText().toString());
            } catch (NumberFormatException e) {
                participationGrade = 0.0;
            }
            Toast.makeText(this, String.valueOf(participationGrade), Toast.LENGTH_LONG).show();
        }
        if (projectEditText.isFocused()) {
            try {
                projectGrade = Double.parseDouble(projectEditText.getText().toString());
            } catch (NumberFormatException e) {
                projectGrade = 0.0;
            }
            Toast.makeText(this, String.valueOf(projectGrade), Toast.LENGTH_LONG).show();
        }
        if (quizzesEditText.isFocused()) {
            try {
                quizzesGrade = Double.parseDouble(quizzesEditText.getText().toString());
            } catch (NumberFormatException e) {
                quizzesGrade = 0.0;
            }
            Toast.makeText(this, String.valueOf(quizzesGrade), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        updateStandard();
    }

    private void updateStandard() {
        double finalMark = assignmentsGrade * 15 / 100 + participationGrade * 15 / 100 + projectGrade * 20 / 100 + quizzesGrade * 20 / 100 + examSeekBarValue * 30 / 100;
        finalMarkEditText.setText(String.format("%.02f", finalMark));
    }
}
