package br.com.testattornatus.restapi.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAddressDto {

    private Long id;
    private String street;
    private String cep;
    private Long number;
    private String city;
    private Boolean mainAddress;
    private Long personId;


}
