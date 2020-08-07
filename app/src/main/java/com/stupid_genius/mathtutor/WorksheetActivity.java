package com.stupid_genius.mathtutor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

public class WorksheetActivity extends AppCompatActivity{
	private MathTutor tutor;
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_worksheet);

		ArrayAdapter<NumberEnum> numberAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, NumberEnum.values());
		ArrayAdapter<OperationEnum> operationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, OperationEnum.values());

		Spinner numberClass = findViewById(R.id.numberClass);
		Spinner operations = findViewById(R.id.operations);
		numberClass.setAdapter(numberAdapter);
		operations.setAdapter(operationAdapter);
	}

	private void createPrintJob(WebView view){
		PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
		String jobName = getString(R.string.app_name) + " Worksheet";
		PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter(jobName);
		PrintJob printJob = printManager.print(jobName, printAdapter, new PrintAttributes.Builder().build());
	}

	public void clickPrint(View view) {
		Spinner numberClass = findViewById(R.id.numberClass);
		Spinner operation = findViewById(R.id.operations);
		Switch allowNegatives = findViewById(R.id.negatives);
		Switch allowImproper = findViewById(R.id.improper);
		EditText sessionCount = findViewById(R.id.sessionCount);
		EditText difficulty = findViewById(R.id.level);

		int count;
		int level;
		String sessionCountInput = String.valueOf(sessionCount.getText());
		String difficultyInput = String.valueOf(difficulty.getText());
		if(sessionCountInput.isEmpty()){
			count = 25;
		}else{
			count = Integer.parseInt(sessionCountInput);
			if(count<1){
				count = 1;
			}
		}
		if(difficultyInput.isEmpty()){
			level = 10;
		}else{
			level = Integer.parseInt(difficultyInput);
			if(level < 10){
				level = 10;
			}else if(level > 99){
				level = 99;
			}
		}

		NumberEnum numberEnum = (NumberEnum) numberClass.getSelectedItem();
		AppWorksheetTemplateLoader templateLoader = new AppWorksheetTemplateLoader();
		templateLoader.setTemplate(numberEnum.getResourceID());
		templateLoader.setApplicationContext(getApplicationContext());
		tutor = new MathTutor();
		tutor.setLoader(templateLoader);
		tutor.startSession(numberEnum, (OperationEnum) operation.getSelectedItem(), count, level, allowNegatives.isChecked(), allowImproper.isChecked());

		webView = new WebView(WorksheetActivity.this);
		webView.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url){
				return false;
			}

			@Override
			public void onPageFinished(WebView view, String url){
				createPrintJob(view);
				webView = null;
			}
		});
		webView.loadDataWithBaseURL(null, tutor.toString(), "text/HTML", "UTF-8", null);
	}
}
