package nconverter;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class NForm extends Form implements CommandListener {
	private final NConverter converter;

	public NForm(String title, NConverter converter) {
		super(title);
		this.converter = converter;
		
		final TextField binary = new TextField("Binary", "", 32, TextField.DECIMAL);
		final TextField decimal = new TextField("Decimal", "", 16, TextField.DECIMAL);
		final TextField hexadecimal = new TextField("Hex", "", 16, TextField.ANY);

		initTextFields(binary, decimal, hexadecimal);

		append(binary);
		append(decimal);
		append(hexadecimal);

		addCommand(new Command("Exit", Command.EXIT, 2));
		setCommandListener(this);
	}

	public void commandAction(Command arg0, Displayable arg1) {
		if (arg0.getCommandType() != Command.EXIT)
			return;
		try {
			converter.destroyApp(false);
		} catch (MIDletStateChangeException e) {
			converter.handleError(e);
		}
	}

	private void initTextFields(final TextField b, final TextField d, final TextField h) {
		final Command convert = new Command("Convert", Command.ITEM, 1);

		b.setDefaultCommand(convert);
		d.setDefaultCommand(convert);
		h.setDefaultCommand(convert);

		b.setItemCommandListener(new NCommand(converter, convert) {
			public void tryAction(Command c, Item item) {
				final long l = Long.parseLong(b.getString(), 2);
				d.setString(Long.toString(l, 10));
				h.setString(Long.toString(l, 16));
			}
		});

		d.setItemCommandListener(new NCommand(converter, convert) {
			public void tryAction(Command c, Item item) {
				final long l = Long.parseLong(d.getString(), 10);
				b.setString(Long.toString(l, 2));
				h.setString(Long.toString(l, 16));
			}
		});

		h.setItemCommandListener(new NCommand(converter, convert) {
			public void tryAction(Command c, Item item) {
				final long l = Long.parseLong(h.getString(), 16);
				b.setString(Long.toString(l, 2));
				d.setString(Long.toString(l, 10));
			}
		});
	}
}
