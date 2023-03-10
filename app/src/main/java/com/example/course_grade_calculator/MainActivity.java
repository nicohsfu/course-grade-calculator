package com.example.course_grade_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener {

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
    Button resetButton;

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

        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(this);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("ASSIGNMENTS")) {
                assignmentsGrade = savedInstanceState.getDouble("ASSIGNMENTS");
                assignmentsEditText.setText(String.valueOf(assignmentsGrade));
            }
            if (savedInstanceState.containsKey("EXAMSEEKBARVALUE")) {
                examSeekBarValue = savedInstanceState.getDouble("EXAMSEEKBARVALUE");
                examSeekBar.setProgress((int) examSeekBarValue);
            }
            if (savedInstanceState.containsKey("PARTICIPATION")) {
                participationGrade = savedInstanceState.getDouble("PARTICIPATION");
                participationEditText.setText(String.valueOf(participationGrade));
            }
            if (savedInstanceState.containsKey("PROJECT")) {
                projectGrade = savedInstanceState.getDouble("PROJECT");
                projectEditText.setText(String.valueOf(projectGrade));
            }
            if (savedInstanceState.containsKey("QUIZZES")) {
                quizzesGrade = savedInstanceState.getDouble("QUIZZES");
                quizzesEditText.setText(String.valueOf(quizzesGrade));
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("ASSIGNMENTS", assignmentsGrade);
        outState.putDouble("EXAMSEEKBARVALUE", examSeekBarValue);
        outState.putDouble("PARTICIPATION", participationGrade);
        outState.putDouble("PROJECT", projectGrade);
        outState.putDouble("QUIZZES", quizzesGrade);
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

    @Override
    public void onClick(View v) {
        assignmentsGrade = 0.0;
        assignmentsEditText.setText("0");
        participationGrade = 0.0;
        participationEditText.setText("0");
        projectGrade = 0.0;
        projectEditText.setText("0");
        quizzesGrade = 0.0;
        quizzesEditText.setText("0");
        examSeekBarValue = 0.0;
        examSeekBarTextView.setText("0");
        examSeekBar.setProgress(0);
        updateStandard();
    }
}
