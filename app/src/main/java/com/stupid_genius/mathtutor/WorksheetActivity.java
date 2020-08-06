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

	private void createWebPrintJob(WebView view){
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

		int count = Integer.parseInt(String.valueOf(sessionCount.getText()));
		int level = Integer.parseInt(String.valueOf(difficulty.getText()));

		if(count<1){
			count = 1;
		}
		if(level < 10){
			level = 10;
		}else if(level > 99){
			level = 99;
		}

		AppWorksheetTemplate template = new AppWorksheetTemplate();
		template.setApplicationContext(getApplicationContext());
		tutor = new MathTutor();
		tutor.setTemplate(template);
		tutor.startSession((NumberEnum) numberClass.getSelectedItem(), (OperationEnum) operation.getSelectedItem(), count, level, allowNegatives.isChecked(), allowImproper.isChecked());

		webView = new WebView(WorksheetActivity.this);
		webView.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url){
				return false;
			}

			@Override
			public void onPageFinished(WebView view, String url){
				createWebPrintJob(view);
				webView = null;
			}
		});
		webView.loadDataWithBaseURL(null, tutor.toString(), "text/HTML", "UTF-8", null);
	}
}
