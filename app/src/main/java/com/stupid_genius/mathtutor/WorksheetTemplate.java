package com.stupid_genius.mathtutor;

import freemarker.template.Template;

public abstract class WorksheetTemplate {
	protected Template worksheetTemplate;

	public abstract void loadTemplate();

	public Template getTemplate() {
		return worksheetTemplate;
	}
}
