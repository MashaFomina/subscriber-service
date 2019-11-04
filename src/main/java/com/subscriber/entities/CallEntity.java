package com.subscriber.entities;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "id_gen", sequenceName = "call_id", allocationSize = 1)
@Table(name = "calls", schema = "subscribers")
public class CallEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
    @Column(nullable = false)
    private long id;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime datetime;

    @Column(nullable = false)
    private float duration;

    @OneToOne
    @JoinColumn(name = "caller_id")
    private SubscriberEntity caller;

    @OneToOne
    @JoinColumn(name = "receiver_id")
    private SubscriberEntity receiver;

    @Column(name="receiver_phone", nullable = false)
    private String receiverPhone;
}
