package com.scprojekt.domain.core.model.user.entity;

import com.scprojekt.domain.core.model.user.event.UserEventType;
import com.scprojekt.domain.core.shared.database.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "userevent")
public class UserEvent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userevent_seq")
    @SequenceGenerator(name = "userevent_seq", sequenceName = "USEREVENT_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "usereventid")
    Long userEventId;

    @Lob
    @Column(name="usereventuuid", unique = true, updatable = false, length = 36)
    @NotNull
    UUID uuid;

    @Enumerated
    @Column(name="usereventtype")
    @NotNull
    UserEventType userEventType;

    @Lob
    @Column(name="userhandlingevent")
    @NotNull
    String userHandlingEvent;

    @Column(name="isremovable")
    @NotNull
    Boolean isRemovable;
}