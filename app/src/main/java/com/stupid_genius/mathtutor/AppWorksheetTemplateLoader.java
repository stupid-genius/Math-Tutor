package com.stupid_genius.mathtutor;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

class AppWorksheetTemplateLoader implements WorksheetTemplateLoader {
	private Context applicationContext;
	private int resourceID;

	public void setTemplate(String templateID){
		resourceID = Integer.parseInt(templateID);
	}

	public void setApplicationContext(Context context){
		applicationContext = context;
	}

	@Override
	public Template loadTemplate() {
		Configuration config = new Configuration();
		config.setDefaultEncoding("UTF-8");
		config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		config.setLogTemplateExceptions(true);
		InputStream ins = applicationContext.getResources().openRawResource(resourceID);
		java.util.Scanner s = new java.util.Scanner(ins).useDelimiter("\\A");
		String template = s.hasNext() ? s.next() : "";
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		stringLoader.putTemplate("worksheet", template);
		config.setTemplateLoader(stringLoader);
		Template worksheetTemplate = null;
		try {
			worksheetTemplate = config.getTemplate("worksheet");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return worksheetTemplate;
	}
}
