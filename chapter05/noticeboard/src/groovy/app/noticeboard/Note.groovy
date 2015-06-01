package app.noticeboard

import java.io.Serializable

@SuppressWarnings("serial")
public class Note implements Serializable {
	
	private int id
	private int positionX
	private int positionY
	private String caption
	private String text = ""
	private int lockedByUser = -1	

	public Note(int id) {
		this.id = id
		caption = "Note " + id
	}

	public Note(int id, int positionX, int positionY, String text) {
		this.id = id
		this.positionX = positionX
		this.positionY = positionY
		this.text = text
	}

}
