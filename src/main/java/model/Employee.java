package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
@Data

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeID;

    private String firstName;

    private String lastName;

    private String email;

    private String jobTitle;

    private String role;

    private String user;

    private String password;

    private Integer isworking = 1;


    @OneToMany(mappedBy = "employee")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Customer> customers = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "managerId")
    private Employee employee;

    @OneToMany(mappedBy = "employee")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Employee> employees = new HashSet<>();

    public String printToConsole() {
        return "Emri: " + this.firstName
                + "\n Mbiemri: " + this.lastName
                + "\n Email :" + this.email
                + "\n Pozicioni punes : " + this.jobTitle
                + "\n Username : " + this.user;
    }


}
