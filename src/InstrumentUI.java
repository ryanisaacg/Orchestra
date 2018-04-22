import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

public class InstrumentUI {
static final int IMAGE_BOX = 128;
	
	private static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	private static final Color[] NOTE_COLORS = {Color.black, Color.black, Color.black, Color.blue, Color.blue, Color.blue, Color.magenta, Color.magenta, Color.magenta, Color.magenta, Color.red, Color.red, Color.red};
	ArrayList<VisualNote> notes = new ArrayList<>();
	ArrayList<Integer> instruments = new ArrayList<>();
	Instrument[] available_instruments;
	Font font = new Font("Consolas", Font.BOLD, 18);
	
	public InstrumentUI() {
		available_instruments = new Instrument[] {
			new Instrument("grand_piano.png", 0, 1),
			new Instrument("piano.png", 2, 5),
			new Instrument("harpsichord.png", 6, 7),
			new Instrument("marimba.png", 8, 15),
			new Instrument("organ.png", 16, 23),
			new Instrument("guitar.png", 24, 31),
			new Instrument("guitar.png", 32, 39), //bass
			new Instrument("violin.png", 40, 47),
			new Instrument("trumpet.png", 56, 56),
			new Instrument("trombone.png", 57, 57),
			new Instrument("flute.png", 71, 78),
			new Instrument("drum.png", 112, 119)
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
		return 960 - 64;
	}
	
	public void paintComponent(Graphics2D g2d) {
		g2d.setFont(font);
		for(int i = 0; i < instruments.size(); i++) {
			int index = findInstrument(instruments.get(i));
			if(index != -1) {
				g2d.drawImage(available_instruments[index].image, findInstrumentX(i), findInstrumentY(i), 96, 96, null);
			}
		}
		for(int i = 0; i < notes.size(); i++) {
			VisualNote note = notes.get(i);
			Color c = NOTE_COLORS[note.note.note];
			g2d.setColor(new Color(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, 1 - note.lifetime / 1000.0f));
			int index = Collections.binarySearch(instruments, note.note.instrument);
			int x = findInstrumentX(index) + (int)(Math.cos(note.rotation * Math.PI / 180) * note.lifetime);
			int y = findInstrumentY(index) - (int)(Math.sin(note.rotation * Math.PI / 180) * note.lifetime);
			if(y < 0) {
				notes.remove(i);
				i--;
			}
			
			g2d.drawString(NOTE_NAMES[note.note.note], x, y);
			note.lifetime += 5;
		}
		g2d.setColor(Color.white);
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
