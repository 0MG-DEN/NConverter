package nconverter;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class NConverter extends MIDlet {
	protected Display display;
	protected Form form;

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		notifyDestroyed();
	}

	protected void pauseApp() {
		notifyPaused();
	}

	protected void startApp() throws MIDletStateChangeException {
		if (form == null) {
			form = new NForm("NConverter", this);
		}
		if (display == null) {
			display = Display.getDisplay(this);
			display.setCurrent(form);
		}
	}

	protected void handleError(Exception e) {
		final String message = e.getMessage();
		final Alert alert = new Alert("Error", message, (Image) null, AlertType.ERROR);
		final Displayable current = display.getCurrent();
		display.setCurrent(alert, current);
	}
}
