package nconverter;

import javax.microedition.lcdui.*;

public class NForm extends Form {
	public NForm(final String title, final NConverter converter) {
		super(title);
		initCommands(converter);
		initTextFields(converter);
	}

	private void initCommands(final NConverter converter) {
		final Command exit = new Command("Exit", Command.EXIT, 2);

		setCommandListener(new NCommand(converter, exit) {
			public void action() throws Exception {
				converter.destroyApp(false);
			}
		});

		addCommand(exit);
	}

	private void initTextFields(final NConverter converter) {
		final TextField b = new TextField("Binary", "", 32, TextField.DECIMAL);
		final TextField d = new TextField("Decimal", "", 16, TextField.DECIMAL);
		final TextField h = new TextField("Hex", "", 16, TextField.ANY);

		final Command convert = new Command("Convert", Command.ITEM, 1);

		b.setItemCommandListener(new NCommand(converter, convert) {
			public void action() {
				final long l = Long.parseLong(b.getString(), 2);
				d.setString(Long.toString(l, 10));
				h.setString(Long.toString(l, 16));
			}
		});

		d.setItemCommandListener(new NCommand(converter, convert) {
			public void action() {
				final long l = Long.parseLong(d.getString(), 10);
				b.setString(Long.toString(l, 2));
				h.setString(Long.toString(l, 16));
			}
		});

		h.setItemCommandListener(new NCommand(converter, convert) {
			public void action() {
				final long l = Long.parseLong(h.getString(), 16);
				b.setString(Long.toString(l, 2));
				d.setString(Long.toString(l, 10));
			}
		});

		b.setDefaultCommand(convert);
		d.setDefaultCommand(convert);
		h.setDefaultCommand(convert);

		append(b);
		append(d);
		append(h);
	}
}
