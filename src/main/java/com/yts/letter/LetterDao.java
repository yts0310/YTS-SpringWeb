package com.yts.letter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;



public class LetterDao {
	static final String INSERT_LETTER = "insert letter(letterId, title, content, senderId, senderName, receiverId, receiverName) values(?,?,?,?,?,?,?)";
	static final String SEND_LIST = "select letterId, title, receiverId, receiverName, cdate from letter where senderId=?";
	static final String RECEIVE_LIST = "select letterId, title, senderId, senderName, cdate from letter where receiverId=?";
	static final String VIEW_LETTER = "select letterId, title, content, senderId, senderName, receiverId, receiverName, cdate from letter where letterId=? and (senderId=? or receiverId=?)";
	static final String DELETE_LETTER = "delete from letter where letterId=? and (senderId=? or receiverId=?)";

	@Autowired
	JdbcTemplate jdbcTemplate;

	RowMapper<Letter> articleRowMapper = new BeanPropertyRowMapper<>(
			Letter.class);
	
	public int addLetter(Letter letter) {
		return jdbcTemplate.update(INSERT_LETTER, letter.getLetterId(), letter.getTitle(), letter.getContent(), letter.getSenderId(), letter.getSenderName(), letter.getReceiverId(), letter.getReceiverName());
	}
	

	public Letter sendList(String senderId) {
		return jdbcTemplate.queryForObject(SEND_LIST, articleRowMapper, senderId);
	}
	
	
	
	public Letter recieveList(String receiverId) {
		return jdbcTemplate.queryForObject(RECEIVE_LIST, articleRowMapper, receiverId);
	}
	
	
	public Letter viewLetter(String letterId, String senderId, String receiverId) {
		return jdbcTemplate.queryForObject(VIEW_LETTER, articleRowMapper,
				letterId, senderId, receiverId);
	}
	
	
	public int deleteLetter(String letterId, String senderId, String reciverId) {
		return jdbcTemplate.update(DELETE_LETTER, letterId, senderId, reciverId);
	}

}
