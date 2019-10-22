package com.demo.contactif.infrastructure.application;

import com.demo.contactif.domain.application.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
