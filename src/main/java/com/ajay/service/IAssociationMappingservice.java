package com.ajay.service;

import java.util.List;

import com.ajay.entity.Person;
import com.ajay.entity.PhoneNo;

public interface IAssociationMappingservice
{
	public String saveData(Person per);
	
	public List<Person> allPersonData();
	
	public List<PhoneNo> allPhonoNO();
	
	public String deletePerson(int id);
	
	public void addChildToParant(int id);
	
}
