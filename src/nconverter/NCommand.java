package nconverter;

import javax.microedition.lcdui.*;

public abstract class NCommand implements ItemCommandListener {
	private final NConverter converter;
	private final Command command;

	public NCommand(NConverter converter, Command command) {
		this.converter = converter;
		this.command = command;
	}

	public void commandAction(Command arg0, Item arg1) {
		if (arg0 != command)
			return;
		try {
			tryAction(arg0, arg1);
		} catch (Exception e) {
			converter.handleError(e);
		}
	}

	public abstract void tryAction(Command arg0, Item arg1);
}
