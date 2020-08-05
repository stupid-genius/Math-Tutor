package com.stupid_genius.mathtutor;

import java.io.File;
import java.io.IOException;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

class CLIWorksheetTemplate extends WorksheetTemplate {
	@Override
	public void loadTemplate() {
		Configuration config = new Configuration();
		try {
			config.setDirectoryForTemplateLoading(new File("app/src/main/res/raw"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		config.setDefaultEncoding("UTF-8");
		config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		config.setLogTemplateExceptions(false);
		try {
			worksheetTemplate = config.getTemplate("worksheet.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
