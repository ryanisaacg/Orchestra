import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Instrument {
	BufferedImage image;
	int minimum, maximum;
	
	public Instrument(String image, int minimum, int maximum) {
		try {
			this.image = ImageIO.read(new File("img/" + image));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.minimum = minimum;
		this.maximum = maximum;
	}
}
