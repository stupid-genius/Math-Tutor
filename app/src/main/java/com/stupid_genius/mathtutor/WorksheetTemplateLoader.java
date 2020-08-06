package com.stupid_genius.mathtutor;

import freemarker.template.Template;

public interface WorksheetTemplateLoader {
	void setTemplate(String templateID);
	Template loadTemplate();
}
