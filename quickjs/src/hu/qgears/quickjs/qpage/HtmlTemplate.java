package hu.qgears.quickjs.qpage;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.lang.StringEscapeUtils;

public class HtmlTemplate {
	protected Writer out;

	public HtmlTemplate(HtmlTemplate parent) {
		this.out=parent.out;
	}
	public HtmlTemplate(Writer out) {
		this.out=out;
	}
	protected void writeHtml(String value) {
		writeObject(StringEscapeUtils.escapeHtml(value));
	}

	protected void writeJSValue(String text) {
		writeObject(StringEscapeUtils.escapeJavaScript(text));
	}

	/**
	 * HTML attribute value
	 * @param key
	 */
	protected void writeValue(String key) {
		writeObject(StringEscapeUtils.escapeHtml(key));
	}

	protected void writeObject(Object o) {
		try {
			out.write(""+o);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	protected void write(String s) {
		try {
			out.write(s);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public Writer getWriter() {
		return out;
	}
	public void setWriter(Writer out) {
		this.out = out;
	}

}
