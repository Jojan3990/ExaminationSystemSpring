package com.rightfindpro.become.repository;

import com.rightfindpro.become.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query("from User  where email = :username or username = :username")
    Optional <User> getUserByEmailOrUserName(@Param("username") String username);
    Optional<User> findByUsername(String username);

    Page<User> findAllByOrderByIdAsc(Pageable pageable);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
