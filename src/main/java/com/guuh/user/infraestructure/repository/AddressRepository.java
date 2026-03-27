package com.guuh.user.infraestructure.repository;

import com.guuh.user.infraestructure.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
