package br.com.testattornatus.restapi.repository;

import br.com.testattornatus.restapi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository <Person, Long> {
}
