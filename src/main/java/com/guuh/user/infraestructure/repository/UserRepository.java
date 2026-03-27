package com.guuh.user.infraestructure.repository;

import com.guuh.user.business.dtos.UserDTO;
import com.guuh.user.infraestructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<UserDTO> findByEmail(String email);

    void deleteByEmail(String email);
}
