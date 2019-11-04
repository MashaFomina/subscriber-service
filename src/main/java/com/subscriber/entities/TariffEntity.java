package com.subscriber.entities;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "id_gen", sequenceName = "tariff_id", allocationSize = 1)
@Table(name = "tariffs", schema = "subscribers")
public class TariffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
    @Column(nullable = false)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "price_call", nullable = false)
    private float priceCall;

    @Column(name = "price_sms", nullable = false)
    private float priceSms;

    @Column(name ="limit_day_calls", unique = true, nullable = false)
    private short limitDayCalls;

    @Column(name = "one_sms_size", nullable = false)
    private short oneSmsSize;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy="tariff", fetch = FetchType.EAGER, cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    private List<SubscriberEntity> subscribers = new ArrayList<>();
}