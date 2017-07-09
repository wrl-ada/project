package org.tarena.note.web.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class AddNoteController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult execute(Note note){
		NoteResult result = 
			noteService.addNote(note);
		return result;
	}
	
}



