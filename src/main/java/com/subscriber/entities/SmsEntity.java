package com.subscriber.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "id_gen", sequenceName = "sms_id", allocationSize = 1)
@Table(name = "sms", schema = "subscribers")
public class SmsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
    @Column(nullable = false)
    private long id;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime datetime;

    @Column(nullable = false)
    private short size;

    @Column(nullable = false)
    private String text;

    @OneToOne
    @JoinColumn(name = "sender_id")
    private SubscriberEntity sender;

    @OneToOne
    @JoinColumn(name = "receiver_id")
    private SubscriberEntity receiver;

    @Column(name="receiver_phone", nullable = false)
    private String receiverPhone;
}
