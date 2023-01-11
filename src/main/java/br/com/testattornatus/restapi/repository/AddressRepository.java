package br.com.testattornatus.restapi.repository;

import br.com.testattornatus.restapi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository <Address, Long> {
}
