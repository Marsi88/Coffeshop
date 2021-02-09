package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
@Data
public class Customer extends AbstractEntity{

    private String firstName;

    private String lastName;

    private String email;

    private Integer phone;

    private String address;

    private String city;

    private String country;


    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Payment> payments = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "employeeID")
    private Employee employee;

    public String printToConsole() {
        return "Emri: " + this.firstName
                + "\n Mbiemri: " + this.lastName
                + "\n Email :" + this.email
                + "\n Numri telefonit : " + this.phone
                + "\n Qyteti : " + this.city;
    }


}