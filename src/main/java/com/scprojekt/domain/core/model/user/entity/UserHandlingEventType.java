package com.scprojekt.domain.core.model.user.entity;

import com.scprojekt.domain.core.shared.database.BaseEntity;
import com.scprojekt.domain.core.shared.database.SQLInjectionSafe;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "userhandlingeventtype")
public class UserHandlingEventType extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @SQLInjectionSafe
    @Column(nullable = false)
    String eventType;

    @SQLInjectionSafe
    @Column(name="benutzerdescription")
    String eventDescription;
}
