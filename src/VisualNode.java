class VisualNote {
		Note note;
		int lifetime = 0;
		int rotation; 
		
		VisualNote(Note note) {
			this.note = note;
			rotation = 90 + (int)((Math.random() - 0.5) * 25);
		}}