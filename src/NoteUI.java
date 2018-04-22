import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class NoteUI {
	ArrayList<VisualNote> notes = new ArrayList<>();
	BufferedImage staff;
	
	public NoteUI() {
		try {
			staff = ImageIO.read(new File("custom_img/staff.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics2D g2d) {
		g2d.drawImage(staff, 0, 0, null);
	}
	
	public void addNote(Note note) {
		notes.add(new VisualNote(note));
	}
}
