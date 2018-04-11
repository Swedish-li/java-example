package com.lrs.common.model;

import javax.xml.bind.annotation.*;
import java.util.List;

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
    @XmlElement(name = "Cards")
    public Cards cards;

    public People() {
    }

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

    public static class Cards {
        @XmlElement(name = "card")
        public List<String> cards;

        public Cards() {
        }

        public Cards(List<String> cards) {
            this.cards = cards;
        }
    }

}
