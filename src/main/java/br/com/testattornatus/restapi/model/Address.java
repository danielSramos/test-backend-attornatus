package br.com.testattornatus.restapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String cep;
    private Long number;
    private String city;
    private Boolean mainAddress;

    @ManyToOne()
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private Person person;

}
