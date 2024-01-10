package com.qualitybazar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qualitybazar.model.Address;

public interface AddressRepository extends JpaRepository<Address,Long> {

}
