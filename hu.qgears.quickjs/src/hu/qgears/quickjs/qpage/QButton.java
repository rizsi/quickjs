package hu.qgears.quickjs.qpage;

import java.io.IOException;

import hu.qgears.commons.UtilEvent;

public class QButton extends QComponent
{
	public final UtilEvent<QButton> clicked=new UtilEvent<>();
	public QButton(QPage page, String identifier) {
		super(page, identifier);
	}
	
	public void generateExampleHtmlObject(HtmlTemplate parent) {
		new HtmlTemplate(parent){
			public void generate() {
				write("<button id=\"");
				writeObject(id);
				write("\">BUTTON</button>\n");
			}
		}.generate();		
	}

	public void handle(HtmlTemplate parent, IInMemoryPost post) throws IOException {
		clicked.eventHappened(this);
	}

	@Override
	public void doInit() {
		setParent(page.getCurrentTemplate());
		write("\tnew QButton(page, \"");
		writeObject(id);
		write("\");\n");
		setParent(null);
	}
}
