package com.stupid_genius.mathtutor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	TextView firstNumber;
	TextView secondNumber;
	TextView operator;
	TextView answer;
	TextView result;

	private MathTutor tutor;
	private SimpleProblem problem;

	public MainActivity() {
		tutor = new MathTutor();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		firstNumber = findViewById(R.id.firstNumber);
		secondNumber = findViewById(R.id.secondNumber);
		operator = findViewById(R.id.operator);
		answer = findViewById(R.id.answer);
		result = findViewById(R.id.result);

		tutor.startSession();
		startProblem();
		setResult("");
	}

	private void setFirstNumber(CharSequence s) {
		firstNumber.setText(s, TextView.BufferType.NORMAL);
	}

	private void setSecondNumber(CharSequence s) {
		secondNumber.setText(s, TextView.BufferType.NORMAL);
	}

	private void setOperator(CharSequence s) {
		operator.setText(s, TextView.BufferType.NORMAL);
	}

	private Integer getAnswer(){
		return Integer.valueOf(answer.getText().toString());
	}
	private void setAnswer(CharSequence s) {
		answer.setText(s, TextView.BufferType.NORMAL);
	}

	private void appendAnswer(CharSequence s){
		answer.append(s);
	}

	private void setResult(CharSequence s){
		result.setText(s, TextView.BufferType.NORMAL);
	}

	public void numberClick(View button) {
		TextView digit = (TextView) button;
		appendAnswer(digit.getText());
	}

	public void clearClick(View button){
		setAnswer("");
	}

	public void submitClick(View view) {
		Integer userAnswer = getAnswer();
		boolean isCorrect = problem.checkAnswer(userAnswer);
		tutor.recordAnswer(isCorrect);
		if (isCorrect) {
			setResult("CORRECT!");
		} else {
			setResult(String.format("incorrect: %s %d", problem.toString(), userAnswer));
		}
		if (tutor.hasNext()) {
			startProblem();
		} else {
			// something else
		}
	}

	private void startProblem(){
		problem = tutor.next();
		setFirstNumber(problem.getFirstNumber().toString());
		setSecondNumber(problem.getSecondNumber().toString());
		setOperator(problem.getOperator());
		setAnswer("");
//		setResult("");
	}
}
