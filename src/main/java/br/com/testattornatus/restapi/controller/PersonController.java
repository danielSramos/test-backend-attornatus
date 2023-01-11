package br.com.testattornatus.restapi.controller;

import br.com.testattornatus.restapi.dto.PersonDto;
import br.com.testattornatus.restapi.service.PersonService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping
    public Page<PersonDto> getAll(@PageableDefault(size = 5)Pageable pagination) {
        return personService.getAll(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable @NotNull Long id) {
        PersonDto dto = personService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PersonDto> create(@RequestBody PersonDto dto, UriComponentsBuilder uriBuilder) {
        PersonDto person = personService.create(dto);
        URI uri = uriBuilder.path("/people/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).body(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> update(@PathVariable @NotNull Long id , @RequestBody PersonDto dto) {
        PersonDto personUpdate = personService.update(id, dto);
        return ResponseEntity.ok(personUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable @NotNull Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
