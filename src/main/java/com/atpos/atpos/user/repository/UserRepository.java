package com.atpos.atpos.user.repository;

import com.atpos.atpos.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(path = "usuarios")
public interface UserRepository extends JpaRepository<User, Long>{

    @RestResource(path = "buscar-username")
    public Optional<User> findByUsername(@Param("username") String username);

}
