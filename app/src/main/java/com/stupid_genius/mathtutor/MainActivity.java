package com.stupid_genius.mathtutor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
	private MathTutor tutor;
	TextView firstNumber;
	TextView secondNumber;
	TextView answer;
	TextView input;

	public MainActivity() {
		tutor = new MathTutor();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		firstNumber = findViewById(R.id.firstNumber);
		secondNumber = findViewById(R.id.secondNumber);
		answer = findViewById(R.id.answer);
		View answerLayout = findViewById(R.id.answerLayout);
		input = findViewById(R.id.input);
		answerLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				input.requestFocus();
				InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				inputMethodManager.showSoftInput(input, 0);
			}
		});
		input.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				setAnswer(s);
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		tutor.startSession();
		setAnswer("");
	}

	private void setFirstNumber(CharSequence s) {
		firstNumber.setText(s, TextView.BufferType.NORMAL);
	}

	private void setSecondNumber(CharSequence s) {
		secondNumber.setText(s, TextView.BufferType.NORMAL);
	}

	private void setAnswer(CharSequence s) {
		answer.setText(s, TextView.BufferType.NORMAL);
	}
}
