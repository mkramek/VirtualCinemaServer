package com.cinema.virtualcinema;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VirtualCinemaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtualCinemaServerApplication.class, args);
    }

}
