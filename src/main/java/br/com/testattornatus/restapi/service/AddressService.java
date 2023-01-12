package br.com.testattornatus.restapi.service;

import br.com.testattornatus.restapi.dto.AddressDto;
import br.com.testattornatus.restapi.model.Address;
import br.com.testattornatus.restapi.model.Person;
import br.com.testattornatus.restapi.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Field;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<AddressDto> getAll(Pageable pagination) {
        return repository.findAll(pagination).map(p -> modelMapper.map(p, AddressDto.class));
    }

    public AddressDto getById(Long id) {
        Address address = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(address, AddressDto.class);
    }

    public AddressDto create(AddressDto dto) {
        Address address = modelMapper.map(dto, Address.class);
        repository.save(address);
        return modelMapper.map(address, AddressDto.class);
    }

    public AddressDto update(Long id, AddressDto dto) {

        Address address = repository.findById(id).get();

        if (dto.getStreet() != null) address.setStreet(dto.getStreet());
        if (dto.getCep() != null) address.setCep(dto.getCep());
        if (dto.getNumber() != null) address.setNumber(dto.getNumber());
        if (dto.getCity() != null) address.setCity(dto.getCity());

        address.setId(id);
        repository.save(address);
        return modelMapper.map(address, AddressDto.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
