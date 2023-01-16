package br.com.testattornatus.restapi.repository;

import br.com.testattornatus.restapi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository <Address, Long> {
    List<Address> findAddressByPersonId(Long id);

    @Query(value = "SELECT A.* FROM ADDRESS A JOIN PEOPLE P ON A.MAIN_ADDRESS = TRUE AND P.ID = A.PERSON_ID WHERE PERSON_ID = ?;",
    nativeQuery = true)
    Address findMainAddressByPersonId(@Param("id") Long id);
}
