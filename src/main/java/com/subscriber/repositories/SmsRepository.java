package com.subscriber.repositories;

import com.subscriber.entities.SmsEntity;
import com.subscriber.entities.SubscriberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface SmsRepository extends CrudRepository<SmsEntity, Long> {
    List<SmsEntity> findAll();
}

