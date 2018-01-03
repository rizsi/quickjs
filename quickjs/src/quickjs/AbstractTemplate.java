package quickjs;

import java.io.IOException;
import java.io.Writer;

public class AbstractTemplate {
	private Writer wr;
	protected void setWriter(Writer wr)
	{
		this.wr=wr;
	}
	protected void write(String string) throws IOException {
		wr.write(string);
	}
	protected void writeObject(Object o) throws IOException {
		wr.write(""+o);
	}
}
