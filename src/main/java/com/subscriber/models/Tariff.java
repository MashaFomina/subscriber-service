package com.subscriber.models;

import lombok.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class Tariff implements Serializable {
    @NotEmpty
    private long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private float priceCall;

    @NotEmpty
    private float priceSms;

    @NotEmpty
    private short limitDayCalls;

    @NotEmpty
    private short oneSmsSize;

    @NotEmpty
    @JsonFormat(pattern = "dd.MM.yyyy hh:mm:ss")
    private LocalDateTime createdAt;

    @NotEmpty
    @Builder.Default
    private List<SubscriberBase> subscribers = new ArrayList();
}
