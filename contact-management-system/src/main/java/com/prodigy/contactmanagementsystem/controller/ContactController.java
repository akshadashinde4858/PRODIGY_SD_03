package com.prodigy.contactmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodigy.contactmanagementsystem.entity.Contact;
import com.prodigy.contactmanagementsystem.service.ContactService;

@RestController
@RequestMapping("/cms")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@GetMapping("/contacts/user/{userid}")
	public ResponseEntity<List<Contact>> getAllContactsByUser(@PathVariable long userid) {
		return ResponseEntity.ok().body(contactService.getAllContactsByUser(userid));
	}
	
	@GetMapping("/contacts")
	public ResponseEntity<List<Contact>> getAllContacts() {
		return ResponseEntity.ok().body(contactService.getAllContacts());
	}

	@GetMapping("/contact/{id}")
	public ResponseEntity<Contact> getContactById(@PathVariable long id) {
		return ResponseEntity.ok().body(contactService.getContactById(id));
	}

	@PostMapping("/contact")
	public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
		return ResponseEntity.ok().body(this.contactService.createContact(contact));
	}

	@PutMapping("/contact/{id}")
	public ResponseEntity<Contact> updateContact(@PathVariable long id, @RequestBody Contact contact) {
		contact.setContactId(id);
		return ResponseEntity.ok().body(this.contactService.createContact(contact));
	}

	@DeleteMapping("/contact/{id}")
	public HttpStatus deleteContact(@PathVariable long id) {
		this.contactService.deleteContact(id);
		return HttpStatus.OK;
	}
}
