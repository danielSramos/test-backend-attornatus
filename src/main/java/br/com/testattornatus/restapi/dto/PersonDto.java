package br.com.testattornatus.restapi.dto;

import br.com.testattornatus.restapi.model.Address;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonDto {

    private Long id;
    @NotNull
    private String name;
    private String birthDate;
    private List<Address> address;

}
