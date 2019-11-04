package com.subscriber.models;

import lombok.*;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Tariff extends TariffBase implements Serializable {
    @NotEmpty
    @Builder.Default
    private List<SubscriberBase> subscribers = new ArrayList();
}
