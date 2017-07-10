package com.tr1nks.model.repositories;

import com.tr1nks.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link JpaRepository JpaRepository} для {@link UserEntity UserEntity}
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
//    @Query("select u from UserEntity u where u.login=:login")
//    UserEntity findByLogin(@Param("login") String login);

    UserEntity findByLogin(String login);
}
