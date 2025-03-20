package com.ajay.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ajay.entity.Person;
import com.ajay.entity.PhoneNo;
import com.ajay.service.IAssociationMappingservice;

@Component
public class PersonAndMobileRunner implements CommandLineRunner {

	@Autowired
	private IAssociationMappingservice service;

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("Menu");
			System.out.println("\t\t1 Add Person");
			System.out.println("\t\t2 Get All Record"
					+ "\n\t\t3 Get Phone to parant "
					+ "\n\t\t4 Delete Record \n"
					+ "\t\t 5 Additing child to existing Parannt \n"
					+ "");
			
			int k = sc.nextInt();

			switch (k) {

			case 1: {

				/*Here I take user name and address to save in database */
				System.out.println("Enter Person Name: ");
				String name = sc.next();

				System.out.println("Enter Person Address: ");
				String add = sc.next();

				Person per = new Person(name, add);

				/*Creating set object for string object of set so that can save into person */
				Set<PhoneNo> list = new HashSet<PhoneNo>();

				System.out.println("How Many Phono You have ");
				int i = sc.nextInt();
				int v = 0;
				while (v < i) {
					/*Taking phone no data from user and save into set */
					System.out.println("Enter " + k + " Phone No Data: ");
					sc.nextLine();
					System.out.print("Enter " + k + " Phone Number: ");
					Long phono = sc.nextLong();

					System.out.print("\nEnter " + k + " Provider Company: ");
					String provider = sc.next();

					System.out.print("\nEnter " + k + " Number Type: ");
					String type = sc.next();

					PhoneNo phno = new PhoneNo(phono, provider, type);
					list.add(phno);
					k++;

				}

				for (PhoneNo phoneNo : list) {
					phoneNo.setPersonInfo(per);
				}

				per.setContractDetails(list);

				String data = service.saveData(per);
				System.out.println(data);
				System.out.println("Person and id Data saved");

			}
				break;

			case 2:
			{
				System.out.println("Printing Data from Parant to Child papant is Person and child is phono ");
				List<Person> list = service.allPersonData();
				
				list.forEach(per -> {

					System.out.println(per);

					Set<PhoneNo> child = per.getContractDetails();

					child.forEach(System.out::println);

				});
				
				break;
			}
			case 3:
			{
				
				
				System.out.println("Getting all Phone data with parant ");
				
				 service.allPhonoNO().forEach(person->{
					 
					 System.out.println(person);
					 Person per=person.getPersonInfo();
					 
					 System.out.println(per);
					 
				 });
				 
				 ;
				
				
				break;
			}
			case 4:
			{
				System.out.println("Enter Person id to delete: ");
				int id=sc.nextInt();
				
				String person = service.deletePerson(id);
				System.out.println(person);
				
				break;
			}
			case 5:
			{
				System.out.println("Enter Parant Id to additing Child: ");
				int id=sc.nextInt();
				
				service.addChildToParant(id);
				
				
				break;
			}
			default: 
			{
				throw new IllegalAccessError("Invalid ");
			}
			
			}

		}

	}

}
