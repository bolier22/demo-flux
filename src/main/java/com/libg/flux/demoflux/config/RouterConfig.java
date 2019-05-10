package com.libg.flux.demoflux.config;

import com.libg.flux.demoflux.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@EnableWebFlux
@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(UserHandler userHandler){
        return RouterFunctions.route(RequestPredicates.GET("/user/findAll"),userHandler::findAll)
                .andRoute(RequestPredicates.GET("/user/getById/{id}"),userHandler::getById)
                .andRoute(RequestPredicates.GET("/user/deleteById/{id}"),userHandler::deleteById);
    }
}
