package org.kryptonmlt.securecredit.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Kurt
 */
@Entity
@Table(name = "creditcard")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;

    private short month;

    private short year;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "scuser_id")
    private User user;

    public CreditCard() {
    }

    public CreditCard(Long id, String number, short month, short year, User user) {
        this.id = id;
        this.number = number;
        this.month = month;
        this.year = year;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public short getMonth() {
        return month;
    }

    public void setMonth(short month) {
        this.month = month;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "CreditCard{" + "id=" + id + ", number=" + number + ", month=" + month + ", year=" + year + ", user=" + user + '}';
    }

}
