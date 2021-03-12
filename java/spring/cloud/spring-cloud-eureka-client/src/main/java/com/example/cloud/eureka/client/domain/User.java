package com.example.cloud.eureka.client.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author jxiu
 * @date 2021/1/13
 */
@Data
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue()
    public Long id;

    @Column(name = "name",length = 40)
    public String name;

    @Column(name = "age")
    public Integer age;

    @Column(name = "gender")
    public Integer gender;

    @Column(name = "nick_name", length = 80)
    public String nickName;

    @Column(name = "email")
    public String email;

    @Column(name = "phone", length = 20)
    public String phone;

    @Column(name = "address")
    public String address;

    @Column(name = "create_time")
    public LocalDateTime createTime;

    @Column(name = "update_time")
    public LocalDateTime updateTime;
}
