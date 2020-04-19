package com.sk.chatapp.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.sk.chatapp.model.Contact;

@Repository
public class ContactsDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public ContactsDao(DataSource datasource) {
		
		jdbcTemplate = new NamedParameterJdbcTemplate(datasource);
	}
	
	public List<Contact> selectAll(String currentUser){
		
		String sql = "SELECT user_id,username from Users where username != :currentuser";
		
		SqlParameterSource inparams = new MapSqlParameterSource()
				.addValue("currentuser", currentUser);
		
		return jdbcTemplate.query(sql,inparams,new BeanPropertyRowMapper(Contact.class));
		
	}
	
	
	
}
