import java.util.function.Consumer;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

class NoteReceiver implements Receiver {
	Consumer<Note> noteCallback;
	
	public NoteReceiver(Consumer<Note> noteCallback) {
		this.noteCallback = noteCallback;
	}
	
	@Override
	public void send(MidiMessage message, long timeStamp) {
		if(message instanceof ShortMessage) {
			ShortMessage sm = (ShortMessage)message;
			if (sm.getCommand() == ShortMessage.NOTE_ON) {
				noteCallback.accept(new Note(sm.getChannel(), sm.getData1() % 12));
			}
	    }
	}
	
	@Override
	public void close() {
		
	}

}
