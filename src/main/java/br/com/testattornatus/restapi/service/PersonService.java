package br.com.testattornatus.restapi.service;

import br.com.testattornatus.restapi.dto.CreatePersonDto;
import br.com.testattornatus.restapi.dto.PersonDto;
import br.com.testattornatus.restapi.model.Person;
import br.com.testattornatus.restapi.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PersonDto> getAll(Pageable pagination) {
        return repository.findAll(pagination).map(p -> modelMapper.map(p, PersonDto.class));
    }

    public PersonDto getById(Long id) {
        Person person = repository.findById(id).orElseThrow( () -> new EntityNotFoundException());
        return modelMapper.map(person, PersonDto.class);
    }

    public CreatePersonDto create(CreatePersonDto dto) {
        Person person = modelMapper.map(dto, Person.class);
        repository.save(person);
        return modelMapper.map(person, CreatePersonDto.class);
    }

    public PersonDto update(Long id, PersonDto dto) {
        Person person = repository.findById(id).get();

        if (dto.getName() != null) person.setName(dto.getName());
        if (dto.getBirthDate() != null) person.setBirthDate(dto.getBirthDate());

        person.setId(id);
        person = repository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
