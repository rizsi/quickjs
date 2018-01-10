package hu.qgears.quickjs.qpage.example;

import hu.qgears.quickjs.qpage.QPage;
import hu.qgears.quickjs.qpage.QSelect;
import hu.qgears.quickjs.qpage.QSelectFastScroll;

/**
 * A simple example of a QPage based web application. 
 */
public class QExample03 extends QExample02
{

	@Override
	protected QSelect createQSelect(QPage page, String string) {
		return new QSelectFastScroll(page, string);
	}
}
