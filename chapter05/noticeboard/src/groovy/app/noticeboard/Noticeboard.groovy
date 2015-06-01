package app.noticeboard

import com.github.wolfie.refresher.Refresher
import com.vaadin.event.FieldEvents.BlurListener
import com.vaadin.event.FieldEvents.FocusListener
import com.vaadin.event.FieldEvents.TextChangeListener
import com.vaadin.ui.AbstractTextField.TextChangeEventMode
import com.vaadin.ui.Button
import com.vaadin.ui.Button.ClickListener
import com.vaadin.ui.Label
import com.vaadin.ui.Layout
import com.vaadin.ui.TextArea
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.Window

@SuppressWarnings("serial")
public class Noticeboard extends VerticalLayout {
	private static notes = []
	private windows = []
	private static int userCount
	private int userId
	private static int noteId = 1
	private Refresher refresher = new Refresher()
	private static final int UPDATE_INTERVAL = 2000

	Noticeboard() {
		refresher.refreshInterval = UPDATE_INTERVAL
		userId = userCount++
		spacing = true
		margin = true
		addComponent(new Label("Logged as an User ${userId}"))
		
		Button addNoteButton = new Button("Add note")
		addNoteButton.addClickListener({ event ->
			Note note = new Note(noteId++)
			notes << note
			Window window = createWindow(note)
			windows << window
			UI.getCurrent().addWindow(window)
		} as ClickListener)

		addComponent(addNoteButton)
		addExtension(refresher)
		new NoticeboardUpdater().start()
	}

	private Window createWindow(final Note note) {
		final Window window = new Window(note.caption)
		Layout layout = new VerticalLayout()
		layout.addComponent(createContentNote(note, window))
		window.with {
			content = layout
			resizable = false
			positionX = note.positionX
			positionY = note.positionY
			data = note
			addBlurListener(createBlurListener(window))
			addFocusListener(createFocusListener(window))
		}
		return window
	}

	private TextArea createContentNote(final Note note, final Window window) {
		TextArea contentNote = new TextArea()
		contentNote.with {
			setSizeFull()
			value = note.text
			immediate = true
			textChangeEventMode = TextChangeEventMode.EAGER
			addBlurListener(createBlurListener(window))
			addFocusListener(createFocusListener(window))
			addTextChangeListener({ event ->
				note.text = event.text
			} as TextChangeListener)
		}
		return contentNote
	}

	private BlurListener createBlurListener(final Window window) {
		return { 
			unlockNote(window)
		} as BlurListener
	}

	private FocusListener createFocusListener(final Window window) {
		return {
			lockNote(window)
		} as FocusListener
	}

	private void lockNote(Window window) {
		Note note = window.data as Note
		note.lockedByUser = userId
		String caption = "LOCKED by User ${userId}"
		note.caption = caption
		window.caption = caption
	}

	private void unlockNote(Window window) {		
		Note note = (Note) window.getData()
		note.with {
			lockedByUser = -1
			positionX = window.positionX
			positionY = window.positionY
			caption = "Note ${note.id}"
		}
		window.caption = "Note ${note.id}"
	}

	private void updateNoticeboard() {
		notes.each { note ->
			Window window = getWindow(note)
			updateTextArea(window, note)

			if (!window) {
				window = createWindow(note)
				windows << window
				UI.getCurrent().addWindow(window)
			}

			if (note.lockedByUser > -1 && note.lockedByUser != userId) {
				window.enabled = false
			} else {
				window.enabled = true
			}
			
			if (note.lockedByUser == userId) {
				Note focusedNote = window.data as Note
				focusedNote.with {
					positionX = window.positionX
					positionY = window.positionY
					caption = window.caption
				}
			} else {
				window.with {
					positionX = note.positionX
					positionY = note.positionY
					caption = note.caption
				}
			}
		}
	}

	private void updateTextArea(Window window, Note note) {
		if (window == null) {
			return
		}
		Layout layout = window.content as Layout
		TextArea area = layout.pop() as TextArea
		area.value = note.text
	}

	private Window getWindow(Note note) {
		windows.each { window ->
			if (window.data.equals(note)) {
				return window
			}
		}
		return null
	}

	public class NoticeboardUpdater extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(UPDATE_INTERVAL)
				} catch (InterruptedException e) {
					e.printStackTrace()
				}

				getUI().getSession().getLockInstance().lock()
				try {
					updateNoticeboard()					
				} finally {
					getUI().getSession().getLockInstance().unlock()
				}
			}
		}
	}

}
