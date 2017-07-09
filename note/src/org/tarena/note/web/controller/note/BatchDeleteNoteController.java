package org.tarena.note.web.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class BatchDeleteNoteController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/batch_delete.do")
	@ResponseBody
	public NoteResult execute(String noteIds){
		String[] ids = noteIds.split(",");
		NoteResult result = noteService.batchDelete(ids);
		return result;
	}
	
}
