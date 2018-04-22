import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.swing.JFrame;

public class Main {
	static final int WIDTH = 1920;
	static final int HEIGHT = 1080;
	
	public static void main(String[] args) throws MidiUnavailableException, FileNotFoundException, IOException, InvalidMidiDataException {
		UI ui = new UI();
		new Thread(() -> {
			try {
				Sequencer seq = MidiSystem.getSequencer();
				seq.setSequence(new BufferedInputStream(new FileInputStream(new File("test.midi"))));
				seq.open();
				seq.getTransmitter().setReceiver(new NoteReceiver(ui::addNote));
				seq.start();
			} catch (MidiUnavailableException | IOException | InvalidMidiDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).run();
		JFrame frame = new JFrame("MIDI Orchestra");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(ui);
		frame.setLayout(null);
		frame.pack();
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		new Thread(() -> {
			while(true) {
				frame.repaint();
				try {
					Thread.sleep(16);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).run();
	}
}
