package com.stupid_genius.mathtutor;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.text.InputType;
import android.util.SizeF;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FractionActivity extends AppCompatActivity {
	TextView firstNumerator;
	TextView firstDenominator;
	TextView secondNumerator;
	TextView secondDenominator;
	TextView operator;
	TextView answerNegative;
	TextView answerNumerator;
	TextView answerDenominator;
	TextView answerWhole;
	TextView answerFocus;
	TextView result;
	TextView resultTop;
	TextView accuracy;
	TextView problemCount;

	Handler timerHandler = new Handler();
	Runnable resultTimer = new Runnable() {
		@Override
		public void run() {
			resultTop.setText("", TextView.BufferType.NORMAL);
		}
	};

	private MathTutor tutor;
	private SimpleProblem problem;
	private OperationEnum operation = OperationEnum.RANDOM;
	private int difficulty = 10;
	private boolean allowNegatives = false;
	private boolean allowImproper = false;

	public FractionActivity(){
		tutor = new MathTutor();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_fraction);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		firstNumerator = findViewById(R.id.firstNumerator);
		firstDenominator = findViewById(R.id.firstDenominator);
		secondNumerator = findViewById(R.id.secondNumerator);
		secondDenominator = findViewById(R.id.secondDenominator);
		operator = findViewById(R.id.operator);
		answerNegative = findViewById(R.id.answerNegative);
		answerNumerator = findViewById(R.id.answerNumerator);
		answerDenominator = findViewById(R.id.answerDenominator);
		answerWhole = findViewById(R.id.answerWhole);
		result = findViewById(R.id.result);
		resultTop = findViewById(R.id.result_top);
		accuracy = findViewById(R.id.accuracy);
		problemCount = findViewById(R.id.totalProblems);

		startSession();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.options_menu, menu);
		MenuItem numberClass = menu.findItem(R.id.classSelected);
		numberClass.setTitle("Fractions");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.allowNegatives:
				item.setChecked(!allowNegatives);
				allowNegatives = item.isChecked();
				break;
			case R.id.difficulty:
				final EditText editView = new EditText(FractionActivity.this);
				editView.setInputType(InputType.TYPE_CLASS_NUMBER);
				AlertDialog dialog = new AlertDialog.Builder(FractionActivity.this)
					.setTitle("Set difficulty")
					.setView(editView)
					.setPositiveButton("Set", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							String value = String.valueOf(editView.getText());
							value = value.substring(0, value.length()>2?2:value.length());
							if (value.isEmpty()) {
								difficulty = 10;
							} else {
								difficulty = Integer.valueOf(value);
								if (difficulty < 10) {
									difficulty = 10;
								}
								if (difficulty > 99) {
									difficulty = 99;
								}
							}
							startSession();
						}
					})
					.setNegativeButton("Cancel", null)
					.create();
				dialog.show();
				return true;
			case R.id.addition:
				operation = OperationEnum.ADDITION;
				break;
			case R.id.subtraction:
				operation = OperationEnum.SUBTRACTION;
				break;
			case R.id.multiplication:
				operation = OperationEnum.MULTIPLICATION;
				break;
			case R.id.division:
				operation = OperationEnum.DIVISION;
				break;
			case R.id.random:
				operation = OperationEnum.RANDOM;
				break;
			case R.id.integers:
				Intent intent = new Intent(FractionActivity.this, MainActivity.class);
				startActivity(intent);
				break;
			case R.id.fractions:
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
		startSession();
		return true;
	}

	private void setFirstNumber(SimpleFraction fraction) {
		firstNumerator.setText(fraction.getNumerator().toString(), TextView.BufferType.NORMAL);
		firstDenominator.setText(fraction.getDenominator().toString(), TextView.BufferType.NORMAL);
	}

	private void setSecondNumber(SimpleFraction fraction) {
		secondNumerator.setText(fraction.getNumerator().toString(), TextView.BufferType.NORMAL);
		secondDenominator.setText(fraction.getDenominator().toString(), TextView.BufferType.NORMAL);
	}

	private void setOperator(CharSequence s) {
		operator.setText(s, TextView.BufferType.NORMAL);
	}

	private void setAnswer(CharSequence s) {
		answerFocus.setText(s);
	}

	private void appendAnswer(CharSequence s) {
		answerFocus.append(s);
	}

	private SimpleFraction getAnswer() {
		int numerator;
		int denominator;
		if (answerWhole.getVisibility() == View.VISIBLE) {
			try {
				numerator = Integer.valueOf(answerWhole.getText().toString());
			} catch (NumberFormatException e) {
				numerator = 0;
			}
			denominator = 1;
		} else {
			try {
				numerator = Integer.valueOf(answerNumerator.getText().toString());
			} catch (NumberFormatException e) {
				numerator = 0;
			}
			try {
				denominator = Integer.valueOf(answerDenominator.getText().toString());
			} catch (NumberFormatException e) {
				denominator = 0;
			}
		}
		if (answerNegative.getVisibility() == View.VISIBLE) {
			numerator *= -1;
		}
		return new SimpleFraction(numerator, denominator);
	}

	private void clearAnswer() {
		answerNumerator.setText("");
		answerDenominator.setText("");
		answerWhole.setText("");
		answerFocus = answerWhole;
		answerNegative.setVisibility(View.GONE);
		answerNumerator.setVisibility(View.GONE);
		answerDenominator.setVisibility(View.GONE);
		answerWhole.setVisibility(View.VISIBLE);
	}

	private void setResult(CharSequence s){
		result.setText(s);
	}

	private void setResultTop(CharSequence s) {
		resultTop.setText(s, TextView.BufferType.NORMAL);
		timerHandler.postDelayed(resultTimer, 1000);
	}

	private void updateStats() {
		Double total = (double) tutor.getTotalProblems();
		Double percent = 100.0;
		if (total > 0) {
			percent = ((double) tutor.getCorrectProblems() / total)*100;
		}
		accuracy.setText(String.format("%d%%", percent.intValue()));
		problemCount.setText(String.format("out of %d", total.intValue()));
	}

	public void switchFocus(View view){
		if (answerWhole.getVisibility() == View.VISIBLE) {
			answerNumerator.setText(answerWhole.getText());
			answerNumerator.setVisibility(View.VISIBLE);
			answerDenominator.setVisibility(View.VISIBLE);
			answerWhole.setVisibility(View.GONE);
			answerFocus = answerDenominator;
		}
	}

	public void numberClick(View button) {
		TextView clickedButton = (TextView) button;
		String buttonText = String.valueOf(clickedButton.getText());
		appendAnswer(buttonText);
	}

	public void negativeClick(View button) {
		answerNegative.setVisibility(answerNegative.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
	}

	public void clearClick(View button) {
		clearAnswer();
	}

	public void submitClick(View view) {
		SimpleFraction userAnswer = getAnswer();
		boolean isCorrect = problem.checkAnswer(userAnswer);
		tutor.recordAnswer(isCorrect);
		updateStats();
		if (isCorrect) {
			setResult("CORRECT!");
			setResultTop("CORRECT!");
		} else {
			setResult(String.format("incorrect: %s %s", problem.toString(), userAnswer.toString()));
			setResultTop("incorrect");
		}
		if (tutor.hasNext()) {
			startProblem();
		} else {
			// something else
		}
	}

	private void startSession() {
		setResult("");
		setResultTop("");
		NumberEnum numClass = allowImproper ? NumberEnum.FRACTION_IMPROPER : NumberEnum.FRACTION;
		tutor.startSession(numClass, operation, difficulty, allowNegatives);
		updateStats();
		startProblem();
	}

	private void startProblem() {
		problem = tutor.next();
		SimpleFraction firstNumber = (SimpleFraction) problem.getFirstNumber();
		SimpleFraction secondNumber = (SimpleFraction) problem.getSecondNumber();
		setFirstNumber(firstNumber);
		setSecondNumber(secondNumber);
		setOperator(problem.getOperator());
		clearAnswer();
	}
}
