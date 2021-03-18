package com.app.model;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String city;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "country")
    private Set<Producer> producers = new LinkedHashSet<Producer>();
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "country")
    private Set<Customer> customer = new LinkedHashSet<Customer>();


    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", producers=" + producers +
                ", customer=" + customer +
                '}';
    }
}
