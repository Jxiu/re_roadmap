package com.example.cloud.eureka.client.repository;

import com.example.cloud.eureka.client.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jxiu
 * @date 2021/1/13
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
