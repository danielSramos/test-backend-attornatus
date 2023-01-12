package br.com.testattornatus.restapi.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePersonDto {

    private Long id;
    @NotNull
    private String name;
    private String birthDate;

}
