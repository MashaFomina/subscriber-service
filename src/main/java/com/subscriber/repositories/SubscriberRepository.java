package com.subscriber.repositories;

import com.subscriber.entities.SubscriberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface SubscriberRepository extends CrudRepository<SubscriberEntity, Long> {
    List<SubscriberEntity> findAll();
    Optional <SubscriberEntity> findByPhone(String phone);
}
