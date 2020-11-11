package com.vitec.vhsm.config;

import com.vitec.vhsm.repository.*;
import com.vitec.vhsm.service.AdminService;
import com.vitec.vhsm.service.HsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public AdminService adminService(){
        return new AdminService(adminRepository());
    }

    @Bean
    public AdminRepository adminRepository(){
        return new JpaAdminRepository(em);
    }

    @Bean
    public HsmService hsmService(){
        return new HsmService(hsmRepository());
    }

    @Bean
    public HsmRepository hsmRepository(){
        return new JpaHsmRepository(em);
    }

}
