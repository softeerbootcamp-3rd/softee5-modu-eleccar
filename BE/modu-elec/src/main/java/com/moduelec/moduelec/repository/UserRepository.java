package com.moduelec.moduelec.repository;

import com.moduelec.moduelec.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
