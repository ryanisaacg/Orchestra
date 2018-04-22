class VisualNote {
		Note note;
		int lifetime = 0;
		int rotation; 
		
		VisualNote(Note note) {
			this.note = note;
			this.lifetime = 0;
			rotation = 90 + (int)((Math.random() - 0.5) * 25);
		}}