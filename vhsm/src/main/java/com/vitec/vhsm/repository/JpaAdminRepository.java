package com.vitec.vhsm.repository;

import com.vitec.vhsm.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaAdminRepository implements AdminRepository {

    private final EntityManager em;

    @Autowired
    public JpaAdminRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Admin save(Admin admin) {
        em.persist(admin);
        return admin;
    }

    @Override
    public Optional<Admin> findById(int id) {
        Admin admin = em.find(Admin.class, id);
        return Optional.ofNullable(admin);
    }

    @Override
    public Optional<Admin> findByUserId(String user_id) {
        List<Admin> result = em.createQuery("select a from Admin a where a.user_id like :user_id", Admin.class)
                .setParameter("user_id", user_id)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Admin> findByName(String user_id, String name) {
        List<Admin> result = em.createQuery("select a from Admin a where (a.user_id like :user_id) and (a.name like :name)", Admin.class)
                .setParameter("user_id", user_id)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Admin> findAll() {
        return em.createQuery("select a from Admin a", Admin.class).getResultList();
    }
}
