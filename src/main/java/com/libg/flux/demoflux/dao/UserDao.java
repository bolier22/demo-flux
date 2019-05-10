package com.libg.flux.demoflux.dao;

import com.libg.flux.demoflux.entity.UserEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserDao {
    //db
    private final ConcurrentMap<Integer, UserEntity> dbMap = new ConcurrentHashMap<>();

    //id 自动生成器
    private final static AtomicInteger idGen = new AtomicInteger();

    public UserDao(){
        dbMap.put(1,new UserEntity(1,"libg",3));
        dbMap.put(2,new UserEntity(2,"Bolier",36));
        dbMap.put(3,new UserEntity(3,"张三",35));
        dbMap.put(4,new UserEntity(4,"李四",63));
        dbMap.put(5,new UserEntity(5,"王五",26));
        dbMap.put(6,new UserEntity(6,"赵六",16));
        dbMap.put(7,new UserEntity(7,"田七",53));
    }

    //return all data flux from 0 to n 个元素
    public Flux<UserEntity> findAll(){
        return Flux.fromIterable(dbMap.values());
    }

    //return one data Mono from 0 to 1 个元素
    public Mono<UserEntity> getById(Integer id){
        if(dbMap.get(id) == null){
            return Mono.empty();
        }
        return Mono.just(dbMap.get(id));
    }

    public Mono<Void> deleteById(Integer id){
        dbMap.remove(id);
        return Mono.empty();
    }
}
