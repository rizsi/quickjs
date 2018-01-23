package hu.qgears.quickjs.qpage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.qgears.commons.UtilFile;

public abstract class QComponent extends HtmlTemplate
{

	protected QPage page;
	protected String id;
	protected boolean inited;
	private List<QComponent> children=new ArrayList<>();

	public QComponent(QPage page, String id) {
		super();
		this.page = page;
		this.id = id;
		if(page!=null)
		{
			page.add(this);
			if(page.inited)
			{
				page.registerToInit(this);
			}
		}
	}

	abstract public void generateExampleHtmlObject(HtmlTemplate parent);

	/**
	 * Must set "inited" field to true!
	 * @param parent
	 */
	final public void init()
	{
		if(!inited)
		{
			doInit();
			inited=true;
		}
		initChildren();
	}
	abstract protected void doInit();

	abstract public void handle(HtmlTemplate parent, IInMemoryPost post) throws IOException;

	final public String getId() {
		return id;
	}
	public QPage getPage() {
		return page;
	}

	public void generateHeader(HtmlTemplate parent)
	{
		new HtmlTemplate(parent){

			public void generate() {
				write("<script language=\"javascript\" type=\"text/javascript\">\n");
				try {
					for(String name: getScriptReferences())
					{
						write(new String(loadJs(name), StandardCharsets.UTF_8));
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				write("</script>\n");
			}
			
		}.generate();
	}

	public byte[] loadJs(String name) throws IOException {
		if(name.equals(getClass().getSimpleName()))
		{
			try {
				return UtilFile.loadFile(getClass().getResource(name+".js"));
			} catch (Exception e) {
				System.err.println("LOADJS: "+getClass().getName()+" "+name);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<String> getScriptReferences() {
		return Collections.singletonList(getClass().getSimpleName());
	}
	public void dispose() {
		for(QComponent c: children)
		{
			c.dispose();
		}
		children.clear();
		setParent(page.getCurrentTemplate());
		write("\tpage.components[\"");
		writeJSValue(id);
		write("\"].dispose();\n");
		setParent(null);
		page.remove(this);
	}

	public void addChild(QComponent child) {
		children.add(child);
	}

	public void initChildren() {
		for(QComponent c: children)
		{
			c.init();
		}
	}
}
