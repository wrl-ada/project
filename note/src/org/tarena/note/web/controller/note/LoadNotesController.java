package org.tarena.note.web.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class LoadNotesController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/loadnotes.do")
	@ResponseBody
	public NoteResult execute(String bookId){
		System.out.println("Ω¯»ÎLoadNotesController");
		NoteResult result = 
			noteService.loadNotes(bookId);
		return result;
	}
	
	
}

