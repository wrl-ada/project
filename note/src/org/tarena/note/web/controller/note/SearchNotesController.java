package org.tarena.note.web.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.SearchNote;
import org.tarena.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class SearchNotesController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/hight_search.do")
	@ResponseBody
	public NoteResult execute(SearchNote search){
		NoteResult result = 
			noteService.searchNotes(search);
		return result;
	}
	
	
}
