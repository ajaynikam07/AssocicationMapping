package com.ajay.service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajay.entity.Person;
import com.ajay.entity.PhoneNo;
import com.ajay.repository.IPersonRepo;
import com.ajay.repository.IPhoneNoRepo;

@Service
public class PhonoNoService implements IAssociationMappingservice 
{
	@Autowired
	private IPersonRepo personRepo;
	@Autowired
	private IPhoneNoRepo phonoRepo;

	 @Override
	public String saveData(Person per) 
	{
		 
		 Person person = personRepo.save(per);
		 
		return "Person Object is save with id:"+person.getPid();
	}

	 Scanner sc=new Scanner(System.in);
	@Override
	public List<Person> allPersonData() 
	{
		return personRepo.findAll();
	}
	
	@Override
	public List<PhoneNo> allPhonoNO() 
	{
	
		return phonoRepo.findAll();
	}
	
	@Override
	public String deletePerson(int id) 
	{
		/*Here it internally generate query to delete record from parant table 
		 * as weel as it delete record from child table also 
		 * we no need to write seprate query to delete record from child table it use relation id to delete record 
		 * */
		
		
	      Optional<Person> byId = personRepo.findById(id);
	      if(byId.isPresent())
	      {
	    	  personRepo.delete(byId.get());	  
	    	  return "Person deleted Successfully";
	      }
		return "Person Not found";
	}
	
	@Override
	public void addChildToParant(int id) 
	{
		 Optional<Person> per = personRepo.findById(id);
		 	
		 if(per.isPresent())
		 {

			 Person person=per.get();	 
			 //Create the new child Object 
			 
			 Set<PhoneNo> childs=person.getContractDetails();
				/*Create the new Child Object */
			 System.out.println("Enter a phono no: ");
			 Long phno=sc.nextLong();
			 
			 System.out.println("Enter company: ");
			 String company=sc.next();
			 
			 System.out.println("Enter Use of No: ");
			 String use=sc.next();
			 
			 
			 PhoneNo ph=new PhoneNo(phno,company,use);
			 
			 // Adding new phono to phono 
			 childs.add(ph);
			 //link child to parant
			 ph.setPersonInfo(person);
			personRepo.save(person);
			
			System.out.println("New Child is added to the existing childs of a Parent ");		  
		 }
		 else
		 {
			 System.out.println(id+" person not found for operation ");
		 }
		
	}
}
