package com.project.bankaccount;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.SocketUtils;

import java.sql.SQLException;

@SpringBootApplication
public class CommandApplication {

    private static final String SPRING_CUCUMBER_PROFILE = "cucumber";
    @Value("${spring.profiles.active:default}")
    String activeProfile;

    private static int h2Port = 9090;

    public static void main(String[] args) {
        SpringApplication.run(CommandApplication.class, args);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseaServer() throws SQLException {

        if (SPRING_CUCUMBER_PROFILE.equals(activeProfile)) {
            h2Port = SocketUtils.findAvailableTcpPort();
        }

        return Server.createTcpServer(
            "-tcp", "-tcpAllowOthers", "-tcpPort", String.valueOf(h2Port));
    }
}