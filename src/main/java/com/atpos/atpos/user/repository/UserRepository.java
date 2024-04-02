package com.atpos.atpos.user.repository;

import com.atpos.atpos.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(path = "usuarios")
public interface UserRepository extends JpaRepository<User, Long>{

    @RestResource(path = "buscar-username")
    Optional<User> findByUsername(@Param("username") String username);

    @RestResource(path = "buscar-id")
    Optional<User> findById(@Param("id") Long id);

}
