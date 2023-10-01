package com.prodigy.contactmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prodigy.contactmanagementsystem.entity.Contact;
import com.prodigy.contactmanagementsystem.exception.ContactNotFoundException;
import com.prodigy.contactmanagementsystem.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	ContactRepository contactRepository;

	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}
	

	public List<Contact> getAllContactsByUser(long userid) {
		return contactRepository.findByUserid(userid);
	}

	public Contact getContactById(long contactId) {
		Optional<Contact> contact = contactRepository.findById(contactId);

		if (contact.isPresent()) {
			return contact.get();
		} else {
			throw new ContactNotFoundException("Contact not found with id : " + contactId);
		}
	}

	public Contact createContact(Contact contact) {
		return contactRepository.save(contact);
	}

	public Contact updateContact(Contact contact) {

		Optional<Contact> savedContact = this.contactRepository.findById(contact.getContactId());

		if (savedContact.isPresent()) {
			Contact contactToUpdate = savedContact.get();

			contactToUpdate.setContactId(contact.getContactId());
			contactToUpdate.setEmail(contact.getEmail());
			contactToUpdate.setName(contact.getName());
			contactToUpdate.setMobile(contact.getMobile());

			contactRepository.save(contactToUpdate);

			return contactToUpdate;
		} else {
			throw new ContactNotFoundException("Contact not found with id : " + contact.getContactId());
		}
	}

	public void deleteContact(long id) {
		Optional<Contact> savedContact = contactRepository.findById(id);

		if (savedContact.isPresent()) {
			contactRepository.delete(savedContact.get());
		} else {
			throw new ContactNotFoundException("Contact not found with id : " + id);
		}
	}

	public Object getAllContactsByUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
