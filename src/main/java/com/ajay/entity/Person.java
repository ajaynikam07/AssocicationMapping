package com.ajay.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="JPA_PERSON")
@Getter
@Setter
@RequiredArgsConstructor
public class Person 
{
	@Id
	@SequenceGenerator(name = "jpa_pid", sequenceName = "jpa_Person_pid", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(generator = "jpa_pid", strategy = GenerationType.SEQUENCE)
	private Integer pid;
	
	@NonNull
	private String name;
	
	@NonNull
	private String address;
	
	/*This is one to many relationship WE have to map our one instance with child class 
	 */
	@OneToMany(targetEntity = PhoneNo.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "personInfo")
	private Set<PhoneNo> contractDetails;

	@Override
	public String toString() 
	{
		return "Person [pid=" + pid + ", name=" + name + ", address=" + address + "]";
	}
	
	public Person() 
	{
		System.out.println("Person::0 Paramter Constructure )");
	}
	
	
}
