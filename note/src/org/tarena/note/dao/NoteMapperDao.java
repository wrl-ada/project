package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.tarena.note.entity.Note;
import org.tarena.note.entity.SearchNote;

public interface NoteMapperDao {
	public List<Note> searchNotes(SearchNote search);
	public List<Map<String,Object>> findNotesByBookId(String bookId);
	public int save(Note note);
	public Note findById(String id);
	/**
	 * ��̬���£�����note����Ϊnull������
	 * @param note
	 * @return
	 */
	public int updateNote(Note note);
	/**
	 * ����ɾ��
	 */
	public int batchDeleteNotes(String[] ids);
	public int batchDeleteNotes1(List<String> ids);
}




