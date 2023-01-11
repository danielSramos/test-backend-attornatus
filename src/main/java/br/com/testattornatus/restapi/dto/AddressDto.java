package br.com.testattornatus.restapi.dto;

import br.com.testattornatus.restapi.model.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

    private Long id;
    private String street;
    private String CEP;
    private Long number;
    private String city;
    private Person person;

}
