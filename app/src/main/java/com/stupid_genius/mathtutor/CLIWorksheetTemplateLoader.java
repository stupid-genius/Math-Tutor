package com.stupid_genius.mathtutor;

import java.io.File;
import java.io.IOException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

class CLIWorksheetTemplateLoader implements WorksheetTemplateLoader {
	private String templateName;

	public void setTemplate(String templateID){
		templateName = templateID;
	}
	@Override
	public Template loadTemplate() {
		Configuration config = new Configuration();
		try {
			config.setDirectoryForTemplateLoading(new File("app/src/main/res/raw"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		config.setDefaultEncoding("UTF-8");
		config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		config.setLogTemplateExceptions(false);
		Template worksheetTemplate = null;
		try {
			worksheetTemplate = config.getTemplate(templateName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return worksheetTemplate;
	}
}
