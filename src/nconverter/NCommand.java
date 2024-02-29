package nconverter;

import javax.microedition.lcdui.*;

public abstract class NCommand implements CommandListener, ItemCommandListener {
	private final NConverter converter;
	private final Command command;

	public NCommand(final NConverter converter, final Command command) {
		this.converter = converter;
		this.command = command;
	}

	public void tryAction() {
		try {
			action();
		} catch (Exception e) {
			converter.handleError(e);
		}
	}

	public void commandAction(Command arg0, Displayable arg1) {
		if (arg0 == command)
			tryAction();
	}

	public void commandAction(Command arg0, Item arg1) {
		if (arg0 == command)
			tryAction();
	}

	public abstract void action() throws Exception;
}
