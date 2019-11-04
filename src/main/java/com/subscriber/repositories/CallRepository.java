package com.subscriber.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import com.subscriber.entities.CallEntity;

@Repository
@Transactional
public interface CallRepository extends CrudRepository<CallEntity, Long> {
    List<CallEntity> findAll();

    @Query("select c from CallEntity c where c.datetime >= :DayAgoDateTime and c.caller.id = :CallerId")
    List<CallEntity> findAllWithDateTimeAfter(
            @Param("DayAgoDateTime") LocalDateTime datetime,
            @Param("CallerId") long id);
}
