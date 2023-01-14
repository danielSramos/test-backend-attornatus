package br.com.testattornatus.restapi.repository;

import br.com.testattornatus.restapi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository <Address, Long> {
    List<Address> findAddressByPersonId(Long id);
}
