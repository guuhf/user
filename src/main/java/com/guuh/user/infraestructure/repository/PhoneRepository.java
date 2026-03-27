package com.guuh.user.infraestructure.repository;

import com.guuh.user.infraestructure.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
