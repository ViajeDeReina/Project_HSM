package com.vitec.vhsm.repository;

import com.vitec.vhsm.domain.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminRepository {
    Admin save(Admin admin);
    Optional<Admin> findById(int id);
    Optional<Admin> findByUserId(String user_id);
    Optional<Admin> findByName(String user_id, String name);
    List<Admin> findAll();
}
