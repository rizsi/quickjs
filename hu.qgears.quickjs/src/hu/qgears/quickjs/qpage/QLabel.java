package hu.qgears.quickjs.qpage;

import java.io.IOException;

import hu.qgears.commons.UtilEventListener;

public class QLabel extends QComponent
{
	public final QProperty<String> innerhtml=new QProperty<>();
	public QLabel(QPage page, String identifier) {
		super(page, identifier);
		innerhtml.serverChangedEvent.addListener(new UtilEventListener<String>() {
			
			@Override
			public void eventHappened(String msg) {
				textChanged(msg);
			}
		});
	}

	public void generateExampleHtmlObject(HtmlTemplate parent) {
		new HtmlTemplate(parent){

			public void generate() {
				write("<div id=\"");
				writeObject(id);
				write("\"></div>\n");
			}
			
		}.generate();		
	}

	public void handle(HtmlTemplate parent, IInMemoryPost post) throws IOException {
	}

	@Override
	public void doInit() {
		setParent(page.getCurrentTemplate());
		write("\tnew QLabel(page, \"");
		writeObject(id);
		write("\").initValue(\"");
		writeJSValue(innerhtml.getProperty());
		write("\");\n");
		setParent(null);
	}
	protected void textChanged(final String msg) {
		if(page.inited)
		{
			new ChangeTemplate(page.getCurrentTemplate()){
				public void generate() {
					write("page.components['");
					writeJSValue(id);
					write("'].initValue(\"");
					writeJSValue(msg);
					write("\");\n");
				}
			}.generate();
		}
	}
}
