package com.yts.letter;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.yts.article.Article;
import com.yts.chap11.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
public class LetterController {
	
	@Autowired
	LetterDao letterDao;
	
	static final Logger logger = LogManager.getLogger();
	
	@GetMapping("/letter/letterForm")
	public String sendLetterForm(HttpSession session) {
		return "letter/letterForm";
	}

	/**
	 * 글 등록
	 */
	@PostMapping("/letter/letter")
	public String sendLetter(Article article,
			@SessionAttribute("MEMBER") Member member) {
		letter;
		setUserId(member.getMemberId());
		article.setName(member.getName());
		articleDao.addArticle(article);
		return "redirect:/app/article/list";
	}
}
