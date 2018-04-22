import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class UI extends JPanel {
	private static final long serialVersionUID = 1L;
	InstrumentUI instruments = new InstrumentUI();
	NoteUI notes = new NoteUI();
	
	@Override
	synchronized public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		instruments.paintComponent(g2d);
		notes.paintComponent(g2d);
		
	}
	
	synchronized public void addNote(Note note) {
		instruments.addNote(note);
		notes.addNote(note);
	}
}