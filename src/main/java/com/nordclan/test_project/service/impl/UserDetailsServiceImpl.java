package com.nordclan.test_project.service.impl;

import com.nordclan.test_project.entity.Employee;
import com.nordclan.test_project.entity.Role;
import com.nordclan.test_project.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    private final EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employeeInfo = employeeRepo.findByUsername(username);

        return User.builder()
                .username(employeeInfo.getUsername())
                .password(employeeInfo.getPassword())
                .roles(String.valueOf(employeeInfo.getRoles().stream().map(Role::getName)))
                .build();
    }
}
