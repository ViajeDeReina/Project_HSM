package com.vitec.vhsm.service;

import com.vitec.vhsm.Role;
import com.vitec.vhsm.domain.Admin;
import com.vitec.vhsm.repository.AdminRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
public class AdminService implements UserDetailsService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    /**
     *
     * 회원 가입
     *
     */
    public String join(Admin admin) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        validateDuplicateAdmin(admin);
        adminRepository.save(admin);
        return admin.getUser_id();
    }

    /**
     *
     * 관리자 중복 검사
     */
    public void validateDuplicateAdmin(Admin admin) {
        adminRepository.findByUserId(admin.getUser_id())
            .ifPresent(m->{
                throw new IllegalStateException("이미 존재하는 관리자입니다.");
            });
    }

    /**
     *
     * 전체 회원 조회
     */
    public List<Admin> findMembers(){
        return adminRepository.findAll();
    }


    /**
     *
     * id로 회원 조회 (ID 중복 검사에 사용)
     *
     */
    public Optional<Admin> findOne(String memberId){

        return adminRepository.findByUserId(memberId);
    }

    /**
    ID, 이름으로 회원 정보 조회
    */
    public Optional<Admin> searchMember(String userId, String userName) {
        return adminRepository.findByName(userId, userName);
    }


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Admin> userEntityWrapper = adminRepository.findByUserId(userId);
        if(userEntityWrapper.isPresent()) {
            Admin userEntity = userEntityWrapper.get();
            if (userEntity.getUse()) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
                return new User(userEntity.getUser_id(), userEntity.getPassword(), authorities);
            }
        }
        return null;
    }
}
