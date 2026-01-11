package com.nofirst.spring.tdd.demo;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.TestcontainersConfiguration;


@SpringBootTest(classes = SpringTddDemoApplication.class)
public abstract class BaseContainerTest {

    // 这里的 mysql:8.0 镜像最好先本地下载，不然启动测试会先尝试下载，导致首次测试时间会变得非常长
    public static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("zhihu")
            .withUsername("root")
            .withPassword("root")
            .withReuse(true);

    @BeforeAll
    public static void start() {
        TestcontainersConfiguration.getInstance().updateUserConfig("testcontainers.reuse.enable", "true");

        mysqlContainer.start();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        // 手动启动
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);

    }


}
