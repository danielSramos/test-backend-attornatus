package br.com.testattornatus.restapi.dto;

import br.com.testattornatus.restapi.model.Person;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

    private Long id;
    private String street;
    private String cep;
    private Long number;
    private String city;
    @NotNull
    private Long personId;

}
