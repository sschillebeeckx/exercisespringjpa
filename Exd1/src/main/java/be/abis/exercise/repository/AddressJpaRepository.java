package be.abis.exercise.repository;

import be.abis.exercise.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressJpaRepository extends JpaRepository<Address,Integer> {

    Address findByStreetAndNrAndZipcodeAndTownIgnoreCase(String street, String nr, String zip, String town);
}
