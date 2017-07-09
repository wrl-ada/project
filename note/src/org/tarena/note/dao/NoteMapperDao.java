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
	 * 动态更新，更新note对象不为null的属性
	 * @param note
	 * @return
	 */
	public int updateNote(Note note);
	/**
	 * 批量删除
	 */
	public int batchDeleteNotes(String[] ids);
	public int batchDeleteNotes1(List<String> ids);
}




