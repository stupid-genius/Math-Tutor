package com.stupid_genius.mathtutor;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

class AppWorksheetTemplate extends WorksheetTemplate {
	Context applicationContext;

	@Override
	public void loadTemplate() {
		Configuration config = new Configuration();
		config.setDefaultEncoding("UTF-8");
		config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		config.setLogTemplateExceptions(true);
		InputStream ins = applicationContext.getResources().openRawResource(R.raw.worksheet);
		java.util.Scanner s = new java.util.Scanner(ins).useDelimiter("\\A");
		String template = s.hasNext() ? s.next() : "";
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		stringLoader.putTemplate("worksheet", template);
		config.setTemplateLoader(stringLoader);
		try {
			worksheetTemplate = config.getTemplate("worksheet");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setApplicationContext(Context context){
		applicationContext = context;
	}
}
