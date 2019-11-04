package com.subscriber.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import com.subscriber.entities.SubscriberEntity;

@Repository
@Transactional
public interface SubscriberRepository extends CrudRepository<SubscriberEntity, Long> {
    List<SubscriberEntity> findAll();
    Optional <SubscriberEntity> findByPhone(String phone);
}
