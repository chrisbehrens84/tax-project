package com.skillstorm.taxappbackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxappbackend.models.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    public Optional<AppUser> findById(Long id);
}
