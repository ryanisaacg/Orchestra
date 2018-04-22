import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

public class InstrumentUI {
static final int IMAGE_BOX = 128;
	
	private static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	
	ArrayList<VisualNote> notes = new ArrayList<>();
	ArrayList<Integer> instruments = new ArrayList<>();
	Instrument[] available_instruments;
	
	public InstrumentUI() {
		available_instruments = new Instrument[] {
			new Instrument("piano.png", 0, 7),
			new Instrument("marimba.png", 8, 15),
			new Instrument("organ.png", 16, 23),
			new Instrument("guitar.png", 24, 31),
			new Instrument("guitar.png", 32, 39), //bass
			new Instrument("violin.jpg", 40, 47),
			new Instrument("trumpet.png", 56, 56),
			new Instrument("trombone.png", 57, 57),
			new Instrument("flute.png", 71, 78),
			new Instrument("drums.jpg", 112, 119)
		};
	}
	
	private int findInstrument(int instrument) {
		for(int i = 0; i < available_instruments.length; i++) {
			if(instrument >= available_instruments[i].minimum && instrument <= available_instruments[i].maximum) {
				return i;
			}
		}
		return -1;
	}
	
	private int findInstrumentX(int index) {
		int cell_size = 1500 / instruments.size();
		return index * cell_size + cell_size / 2;
	}
	
	private int findInstrumentY(int index) {
		return 960;
	}
	
	public void paintComponent(Graphics2D g2d) {
		for(int i = 0; i < instruments.size(); i++) {
			int index = findInstrument(instruments.get(i));
			if(index != -1) {
				g2d.drawImage(available_instruments[index].image, findInstrumentX(i), findInstrumentY(i), 64, 64, null);
			}
		}
		for(int i = 0; i < notes.size(); i++) {
			VisualNote note = notes.get(i);
			g2d.setColor(new Color(0, 0, 0, 1 - note.lifetime / 1000.0f));
			int index = Collections.binarySearch(instruments, note.note.instrument);
			int x = findInstrumentX(index) + (int)(Math.cos(note.rotation * Math.PI / 180) * note.lifetime);
			int y = findInstrumentY(index) - (int)(Math.sin(note.rotation * Math.PI / 180) * note.lifetime);
			if(y < 0) {
				notes.remove(i);
				i--;
			}
			g2d.drawString(NOTE_NAMES[note.note.note], x, y);
			note.lifetime += 3;
		}
	}
	
	void addNote(Note note) {
		if(findInstrument(note.instrument) == -1) return;
		notes.add(new VisualNote(note));
		int index = Collections.binarySearch(instruments, note.instrument);
		if(index < 0) {
			instruments.add(-(index + 1), note.instrument);
		}
	}
}
