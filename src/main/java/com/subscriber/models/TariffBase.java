package com.subscriber.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@EqualsAndHashCode
public class TariffBase implements Serializable {
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
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime createdAt;
}

