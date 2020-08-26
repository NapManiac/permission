package com.fly;

import com.fly.service.DeptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PermissionApplicationTests {
    @Autowired
    private DeptService deptService;

    @Test
    void contextLoads() {
        System.out.println(deptService.queryDeptAll());
    }

}
