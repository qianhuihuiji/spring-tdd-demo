package com.nofirst.spring.tdd.demo;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.TestcontainersConfiguration;


@SpringBootTest(classes = SpringTddDemoApplication.class)
public abstract class BaseContainerTest {

    public static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("zhihu")
            .withUsername("root")
            .withPassword("root")
            .withReuse(true);

    static {
        // 复用配置
        TestcontainersConfiguration.getInstance().updateUserConfig("testcontainers.reuse.enable", "true");
        // 启动容器，确保端口提前分配
        mysqlContainer.start();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);


    }
}
