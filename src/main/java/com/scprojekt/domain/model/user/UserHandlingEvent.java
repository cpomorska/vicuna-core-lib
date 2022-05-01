package com.scprojekt.domain.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserHandlingEvent {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name= "usereventype_id")
    private UserHandlingEventType userHandlingEventType;

    @NotNull
    @Column(name = "eventTriggerdTime")
    private LocalDateTime eventTriggeredTime;
}
