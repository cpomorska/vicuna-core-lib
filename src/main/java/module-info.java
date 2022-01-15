module vicuna.core.lib {
    requires static lombok;
    requires java.persistence;
    requires java.validation;

    exports com.scprojekt.domain.entities;
    exports com.scprojekt.domain.service;
    exports com.scprojekt.domain.interfaces;

    opens com.scprojekt.domain.entities to vicuna.infra;
    opens com.scprojekt.domain.interfaces to vicuna.infra;
}