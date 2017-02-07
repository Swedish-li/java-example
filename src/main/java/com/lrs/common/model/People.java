package com.lrs.common.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "user")  
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class People {
	@XmlAttribute(name = "id")  
    public String id;  
      
    @XmlAttribute(name = "name")  
    public String name;  
      
    @XmlAttribute(name = "age")  
    public int age;  
      
    @XmlAttribute(name = "sex")  
    public String sex;  
      
    @XmlElement(name = "address")  
    public String address;  
      
    @XmlElement(name = "Account")  
    public Account account;  
  
    public static class Account {  
        @XmlAttribute(name = "username")  
        public String username;  
  
        @XmlValue  
        public String password;  
  
        public Account() {  
        }  
  
        public Account(String username, String password) {  
            this.username = username;  
            this.password = password;  
        }  
    }  
      
    @XmlElement(name = "Cards")  
    public Cards cards;  
      
    public static class Cards {  
        @XmlElement(name = "card")  
        public List<String> cards;  
  
        public Cards() {  
        }  
  
        public Cards(List<String> cards) {  
            this.cards = cards;  
        }  
    }  
      
    public People(){}  

}
