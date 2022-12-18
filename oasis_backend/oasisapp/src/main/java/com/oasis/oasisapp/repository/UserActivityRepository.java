package com.oasis.oasisapp.repository;

import com.oasis.oasisapp.model.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

    @Query(value = "SELECT * FROM user_activity WHERE user_activity.user_login=:user_login", nativeQuery = true)
    Optional<List<UserActivity>> getAllByUserLogin(@Param("user_login") String user_login);
}
