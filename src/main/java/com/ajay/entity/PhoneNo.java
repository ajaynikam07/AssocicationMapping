package com.ajay.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "JPA_PHONO")
public class PhoneNo
{
	@Id
	@SequenceGenerator(name="reg1",sequenceName = "Reg_no",initialValue = 100,allocationSize = 1)
	@GeneratedValue(generator = "reg1",strategy = GenerationType.SEQUENCE)
	private Integer regno;

	@NonNull
	private Long mobileNo;
	
	@NonNull
	private String provider;
	
	@NonNull
	private String numberType;
	
	/**/
	@ManyToOne(targetEntity = Person.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "Person_Id",referencedColumnName = "pid")
	private Person personInfo;

    public PhoneNo()
    {
    	System.out.println("PhoneNo.PhoneNo()");
    }

	@Override
	public String toString() {
		return "PhoneNo [regno=" + regno + ", mobileNo=" + mobileNo + ", provider=" + provider + ", numberType="
				+ numberType + "]";
	}
}
