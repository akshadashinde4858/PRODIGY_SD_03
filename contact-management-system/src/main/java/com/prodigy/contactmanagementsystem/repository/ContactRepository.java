package com.prodigy.contactmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.prodigy.contactmanagementsystem.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>  {
	
	List<Contact> findByUserid(@Param("userid") long userid);

}
