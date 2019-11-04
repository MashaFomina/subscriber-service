package com.subscriber.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@EqualsAndHashCode
public class Balance implements Serializable {
    @NotEmpty
    private float balance;

    public Status getStatus() {
        return balance > 0 ? Status.ACTIVE : Status.BLOCKED;
    }
}
