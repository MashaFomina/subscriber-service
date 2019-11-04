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
public class Call implements Serializable {
    @NotEmpty
    private long id;

    @NotEmpty
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime datetime;

    @NotEmpty
    private float duration;

    @NotEmpty
    private SubscriberBase caller;

    private SubscriberBase receiver;

    @NotEmpty
    private String receiverPhone;
}
