import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

public class UI extends JPanel {
	static final int IMAGE_BOX = 256;
	
	private static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	
	class VisualNote {
		Note note;
		int lifetime = 0;
		
		VisualNote(Note note) {
			this.note = note;
		}
	}
	
	ArrayList<VisualNote> notes = new ArrayList<>();
	Instrument[] instruments;
	
	public UI() {
		super();
		instruments = new Instrument[] {
			new Instrument("piano.png", 0, 7),
			new Instrument("marimba.jpg", 8, 15),
			new Instrument("organ.jpg", 16, 23),
			new Instrument("guitar.png", 24, 31),
			new Instrument("bass.jpg", 32, 39),
			new Instrument("violin.jpg", 40, 47),
			new Instrument("trumpet.png", 56, 56),
			new Instrument("trombone.png", 57, 57),
			new Instrument("horn.png", 58, 63),
			new Instrument("sax.png", 64, 67),
			new Instrument("flute.png", 71, 78),
			new Instrument("ocarina.png", 79, 79),
			new Instrument("drums.jpg", 112, 119)
		};
	}
	
	private int findInstrument(int instrument) {
		for(int i = 0; i < instruments.length; i++) {
			if(instrument >= instruments[i].minimum && instrument <= instruments[i].maximum) {
				return i;
			}
		}
		return -1;
	}
	
	private int findInstrumentX(int index) {
		return (index % 5) * IMAGE_BOX  + IMAGE_BOX / 2;
	}
	
	private int findInstrumentY(int index) {
		return (index / 5  + 1) * IMAGE_BOX;
	}
	
	@Override
	public synchronized void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		super.paintComponent(g);
		g2d.setColor(Color.black);
		for(int i = 0; i < instruments.length; i++) {
			g2d.drawImage(instruments[i].image, findInstrumentX(i), findInstrumentY(i), 64, 64, null);
		}
		for(VisualNote note: notes) {
			g2d.drawRect(note.note.instrument * 50, note.lifetime, 5, 5);
			g2d.drawString(NOTE_NAMES[note.note.note], note.note.instrument * 50, note.lifetime);
			note.lifetime++;
		}
	}
	
	synchronized void addNote(Note note) {
		notes.add(new VisualNote(note));
		System.out.println(note);
	}
}