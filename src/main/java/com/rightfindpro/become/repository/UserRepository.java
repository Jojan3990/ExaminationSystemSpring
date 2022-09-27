package com.rightfindpro.become.repository;

import com.rightfindpro.become.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    @Query("from User  where email = :username or username = :username")
    Optional <User> getUserByEmailOrUserName(@Param("username") String username);
    User findByUsername(String username);

    List<User> findAll();

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    void delete(Optional<User> userToDelete);
}
