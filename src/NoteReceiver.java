import java.util.function.Consumer;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

class NoteReceiver implements Receiver {
	InstrumentUI ui;

	@SuppressWarnings("unchecked")
	public NoteReceiver(InstrumentUI ui) {
		this.ui = ui;
	}

	@Override
	public void send(MidiMessage message, long timeStamp) {
		if (message instanceof ShortMessage) {
			ShortMessage sm = (ShortMessage) message;
			if (sm.getCommand() == ShortMessage.NOTE_ON) {
				ui.addNote(new Note(sm.getChannel(), sm.getData1() % 12));
			} else if (sm.getCommand() == ShortMessage.PROGRAM_CHANGE) {
				ui.setInstrument(new InstrumentIndex(sm.getChannel(), sm.getData1()));
			}
		}
	}

	@Override
	public void close() {

	}

}
