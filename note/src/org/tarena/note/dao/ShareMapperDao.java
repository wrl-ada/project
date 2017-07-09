package org.tarena.note.dao;

import java.util.List;

import org.tarena.note.entity.Share;

public interface ShareMapperDao {
	public int save(Share share);
	public Share findByNoteId(String noteId);
	public int deleteByNoteId(String noteId);
	public List<Share> findLikeTitle(String title);
}
