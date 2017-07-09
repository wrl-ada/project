package org.tarena.note.service;

import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.SearchNote;

public interface NoteService {
	public NoteResult batchDelete(String[] ids);
	public NoteResult searchNotes(SearchNote search);
	public NoteResult loadNotes(String bookId);
	public NoteResult addNote(Note note);
	public NoteResult loadNote(String noteId);
	public NoteResult updateNote(Note note);
	public NoteResult deleteNote(String noteId);
	public NoteResult shareNote(String noteId);
	public NoteResult searchShareNotes(String keyword);
}



