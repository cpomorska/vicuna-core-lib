package com.scprojekt.domain.core.model.user.entity;

import com.scprojekt.domain.core.shared.database.BaseEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "userhandlingevent")
public class UserHandlingEvent extends BaseEntity {

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
