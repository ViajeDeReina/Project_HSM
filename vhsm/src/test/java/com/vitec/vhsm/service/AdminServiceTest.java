package com.vitec.vhsm.service;

import com.vitec.vhsm.domain.Admin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceTest {

    AdminService adminService;

    @BeforeEach
    public void beforeEach(){
    }

    @AfterEach
    public void afterEach(){


    }

    @Test
    void 회원가입() {
        //given
        Admin admin = new Admin();
        admin.setName("hello");
        //when
        String saveid = adminService.join(admin);

        //then
        Admin findAdmin = adminService.findOne(saveid).get();
        assertEquals(admin.getId(),saveid);
    }

    @Test
    void 중복_회원_예외() {
        //given
        Admin admin1 = new Admin();
        admin1.setName("spring");

        Admin admin2 = new Admin();
        admin2.setName("spring");
        //when
        adminService.join(admin1);
        assertThrows(IllegalStateException.class, () -> adminService.join(admin2));

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}