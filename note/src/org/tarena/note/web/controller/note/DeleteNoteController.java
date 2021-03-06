package org.tarena.note.web.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class DeleteNoteController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/first_delete.do")
	@ResponseBody
	public NoteResult firstDelete(String noteId){
		NoteResult result = 
			noteService.deleteNote(noteId);
		return result;
	}
	
	
}




