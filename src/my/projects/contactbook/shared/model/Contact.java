package my.projects.contactbook.shared.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contact implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String name;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	private String address;
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}
	private Date birthday;
	
	public String getNumber() {
		return number;
	}
    public void setNumber(String number) {
		this.number = number;
	}
	private  String number;
	
	public String getPhoto() {
		return photo;
	}
    public void setPhoto(String photo) {
		this.photo = photo;
	}
	private  String photo;
	
	public Contact() {}
		
	
	
}	

