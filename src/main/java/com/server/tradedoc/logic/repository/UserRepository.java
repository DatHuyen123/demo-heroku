package com.server.tradedoc.logic.repository;

import com.server.tradedoc.logic.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 *
 * @author DatDV
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByUsernameAndStatus(String username, int status);
}
