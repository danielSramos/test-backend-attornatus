package br.com.testattornatus.restapi.controller;

import br.com.testattornatus.restapi.dto.AddressDto;
import br.com.testattornatus.restapi.model.Address;
import br.com.testattornatus.restapi.service.AddressService;
import com.sun.istack.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public Page<AddressDto> getAll(@PageableDefault(size = 5)Pageable pagination) {
        return addressService.getAll(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getById(@PathVariable @NotNull Long id) {
        AddressDto dto = addressService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AddressDto> create(@RequestBody AddressDto dto, UriComponentsBuilder uriBuilder) {
        AddressDto address = addressService.create(dto);
        URI uri = uriBuilder.path("/address/{id}").buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).body(address);
    }

    @PutMapping("{id}")
    public ResponseEntity<AddressDto> update(@PathVariable @NotNull Long id, @RequestBody AddressDto dto) {
        AddressDto address = addressService.update(id, dto);
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable @NotNull Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
