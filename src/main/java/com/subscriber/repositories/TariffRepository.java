package com.subscriber.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import com.subscriber.entities.TariffEntity;

@Repository
@Transactional
public interface TariffRepository extends CrudRepository<TariffEntity, Long> {
    List<TariffEntity> findAll();
}
