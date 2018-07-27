package org.spring.boot2.first.service;

import org.spring.boot2.first.entity.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

//其中第二个参数为Id的类型
public interface GirlRep extends JpaRepository<Girl, Integer> {
}