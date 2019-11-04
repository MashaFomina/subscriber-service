package com.subscriber.models;

import lombok.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@EqualsAndHashCode
public class Sms implements Serializable {
    @NotEmpty
    private long id;

    @NotEmpty
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime datetime;

    @NotEmpty
    private String text;

    @NotEmpty
    private short size;

    @NotEmpty
    private SubscriberBase sender;

    private SubscriberBase receiver;

    @NotEmpty
    private String receiverPhone;
}
