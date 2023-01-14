package br.com.testattornatus.restapi.service;

import br.com.testattornatus.restapi.dto.CreatePersonDto;
import br.com.testattornatus.restapi.dto.PersonDto;
import br.com.testattornatus.restapi.model.Address;
import br.com.testattornatus.restapi.model.Person;
import br.com.testattornatus.restapi.repository.AddressRepository;
import br.com.testattornatus.restapi.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PersonDto> getAll(Pageable pagination) {
        return personRepository.findAll(pagination).map(p -> modelMapper.map(p, PersonDto.class));
    }

    public PersonDto getById(Long id) {
        Person person = personRepository.findById(id).orElseThrow( () -> new EntityNotFoundException());

        Address mainAddressByPersonId = addressRepository.findMainAddressByPersonId(id);
        List<Address> address = new ArrayList<>();
        address.add(mainAddressByPersonId);
        person.setAddress(address);
        return modelMapper.map(person, PersonDto.class);
    }

    public CreatePersonDto create(CreatePersonDto dto) {
        Person person = modelMapper.map(dto, Person.class);
        personRepository.save(person);
        return modelMapper.map(person, CreatePersonDto.class);
    }

    public PersonDto update(Long id, PersonDto dto) {
        Person person = personRepository.findById(id).get();

        if (dto.getName() != null) person.setName(dto.getName());
        if (dto.getBirthDate() != null) person.setBirthDate(dto.getBirthDate());

        person.setId(id);
        person = personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }

}
