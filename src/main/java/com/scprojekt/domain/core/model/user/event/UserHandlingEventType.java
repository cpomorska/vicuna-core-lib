package com.scprojekt.domain.core.model.user.event;

import com.scprojekt.domain.core.shared.database.SQLInjectionSafe;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserHandlingEventType {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    String eventType;

    @SQLInjectionSafe
    @Column(name="benutzerdescription")
    String eventDescription;
}
