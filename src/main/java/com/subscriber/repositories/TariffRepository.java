package com.subscriber.repositories;

import com.subscriber.entities.SubscriberEntity;
import com.subscriber.entities.TariffEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TariffRepository extends CrudRepository<TariffEntity, Long> {
    List<TariffEntity> findAll();
}
