package dev.taxiapp.taxi_app.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.taxiapp.taxi_app.Entities.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {
    public Optional<UserEntity> findByEmail(String email);
    public Optional<UserEntity> findByPhoneNumber(String phoneNumber);
    Optional<UserEntity> findByEmailOrPhoneNumber(String email, String phoneNumber);

    
}
