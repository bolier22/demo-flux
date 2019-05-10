package com.libg.flux.demoflux.handler;

import com.libg.flux.demoflux.dao.UserDao;
import com.libg.flux.demoflux.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {
    @Autowired
    UserDao userDao;

    public Mono<ServerResponse> findAll(ServerRequest serverRequest){
        return ServerResponse.ok().body(userDao.findAll(), UserEntity.class);
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest){
        return userDao.getById(Integer.valueOf(serverRequest.pathVariable("id")))
                .flatMap(user->ServerResponse.ok().syncBody(user)).switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteById(ServerRequest request){

        return ServerResponse.noContent().build(userDao.deleteById(Integer.valueOf(request.pathVariable("id"))));
    }
}
