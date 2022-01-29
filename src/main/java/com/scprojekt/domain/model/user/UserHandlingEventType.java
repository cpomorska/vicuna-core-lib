package com.scprojekt.domain.model.user;

import com.scprojekt.domain.validation.SQLInjectionSafe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
