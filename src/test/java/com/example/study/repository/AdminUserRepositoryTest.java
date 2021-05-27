package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.AdminUser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class AdminUserRepositoryTest extends StudyApplicationTests {

    @Autowired
    AdminUserRepository adminUserRepository;

    // CRUD

    @Test
    public void create() {

        // 데이터 받기
        String account = "AdminUser01";
        String password = "AdminUser01";
        String status = "REGISTERED";
        String role = "PARTNER";      // 계정의 권한

        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        AdminUser adminUser = new AdminUser();

        adminUser.setAccount(account);
        adminUser.setPassword(password);
        adminUser.setStatus(status);
        adminUser.setRole(role);
        adminUser.setCreatedAt(createdAt);
        adminUser.setCreatedBy(createdBy);

        AdminUser newAdminUser = adminUserRepository.save(adminUser);

        Assert.assertNotNull(newAdminUser);
    }
}
