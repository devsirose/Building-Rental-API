package com.example.application.repository;

import com.example.application.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "SELECT u.* FROM user u, user_role u_s, role r" +
            " WHERE u_s.user_id = u.id AND u_s.role_id = r.id AND r.code = 'staff'", nativeQuery = true)
    List<UserEntity> findAllStaff();

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findUserEntitiesByIdIn(List<Long> Ids);

    Optional<UserEntity> findByEmail(String email);

    boolean existsByUsername(String username);

    Optional<UserEntity> findByEmailAndProvider(String email, String provider);

    Optional<UserEntity> findById(Long id);
}
