package com.subscriber.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import com.subscriber.entities.SmsEntity;

@Repository
@Transactional
public interface SmsRepository extends CrudRepository<SmsEntity, Long> {
    List<SmsEntity> findAll();
}

