package com.technology.springboot.connDb.jpa.demo1;

import org.springframework.data.repository.CrudRepository;

/**
 * spring 会有自动的实现类
 */
public interface UserAddressRepository extends CrudRepository<UserAddress,Long> {
}
