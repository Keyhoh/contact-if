package com.demo.contactif.infrastructure.application;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactKeyRepository extends CrudRepository<ContactKey, String> {
    List<ContactKey> findByUserId(String userId);
}
