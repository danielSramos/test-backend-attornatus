package br.com.testattornatus.restapi.service;

import br.com.testattornatus.restapi.dto.AddressDto;
import br.com.testattornatus.restapi.model.Address;
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
        Address addressUpdated = modelMapper.map(dto, Address.class);
        addressUpdated.setId(id);
        repository.save(addressUpdated);
        return modelMapper.map(addressUpdated, AddressDto.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
