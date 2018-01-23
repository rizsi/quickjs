package hu.qgears.quickjs.qpage;

import java.io.IOException;
import java.io.Writer;

import hu.qgears.commons.EscapeString;

public class HtmlTemplate {
	protected Writer out;

	public HtmlTemplate()
	{
	}
	public HtmlTemplate(HtmlTemplate parent) {
		this.out=parent.out;
	}
	public HtmlTemplate(Writer out) {
		this.out=out;
	}
	protected void setParent(HtmlTemplate parent)
	{
		if(parent!=null)
		{
			this.out=parent.getWriter();
		}else
		{
			this.out=null;
		}
	}
	protected void writeHtml(String value) {
		try {
			EscapeString.escapeHtml(out, value);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	protected void writeJSValue(String text) {
		try {
			EscapeString.escapeJavaScript(out, text);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * HTML attribute value
	 * @param key
	 */
	protected void writeValue(String key) {
		try {
			EscapeString.escapeHtml(out, key);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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
