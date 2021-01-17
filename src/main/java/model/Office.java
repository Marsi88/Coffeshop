package model;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "offices")
@Data

public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer officecode;

    private String city;

    private String country;

    private Integer phone;

    private String address;



    @OneToMany(mappedBy = "office")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Employee> employees = new HashSet<>();


}
