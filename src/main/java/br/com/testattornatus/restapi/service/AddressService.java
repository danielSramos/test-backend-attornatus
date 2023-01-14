package br.com.testattornatus.restapi.service;

import br.com.testattornatus.restapi.dto.AddressDto;
import br.com.testattornatus.restapi.dto.CreateAddressDto;
import br.com.testattornatus.restapi.model.Address;
import br.com.testattornatus.restapi.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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

    public List<AddressDto> getByPersonId(Long id) {

        List<Address> address = repository.findAddressByPersonId(id);
        List<AddressDto> addressDto = modelMapper.map(address, new TypeToken<List<AddressDto>>() {
        }.getType());
        return addressDto;
    }

    public CreateAddressDto create(CreateAddressDto dto) {
        Address address = modelMapper.map(dto, Address.class);
        repository.save(address);
        return modelMapper.map(address, CreateAddressDto.class);
    }

    public AddressDto update(Long id, AddressDto dto) {

        Address address = repository.findById(id).get();

        if (dto.getStreet() != null) address.setStreet(dto.getStreet());
        if (dto.getCep() != null) address.setCep(dto.getCep());
        if (dto.getNumber() != null) address.setNumber(dto.getNumber());
        if (dto.getCity() != null) address.setCity(dto.getCity());

        List<Address> addressByPersonId = repository.findAddressByPersonId(address.getPerson().getId());
        addressByPersonId.forEach(a -> {
            if (a.getMainAddress() == true) {
                a.setMainAddress(false);
            }
        });

        if (dto.getMainAddress() != null) address.setMainAddress(dto.getMainAddress());

        address.setId(id);
        repository.save(address);
        return modelMapper.map(address, AddressDto.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
