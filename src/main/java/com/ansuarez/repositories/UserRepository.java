package com.ansuarez.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ansuarez.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
