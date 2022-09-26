module vicuna.core.lib {
    requires static lombok;
    requires jakarta.persistence;
    requires jakarta.inject;
    requires java.validation;

    exports com.scprojekt.domain.model.assurance;
    exports com.scprojekt.domain.model.banking;
    exports com.scprojekt.domain.model.customer;
    exports com.scprojekt.domain.model.user;

    exports com.scprojekt.domain.service;
    exports com.scprojekt.domain.shared;
    exports com.scprojekt.stereotype;

    opens com.scprojekt.domain.shared to vicuna.infra, vicuna.app;
    opens com.scprojekt.domain.model.user to vicuna.infra, vicuna.app;
    opens com.scprojekt.domain.model.banking to vicuna.infra, vicuna.app;
    opens com.scprojekt.domain.model.assurance to vicuna.infra, vicuna.app;
    opens com.scprojekt.domain.model.customer to vicuna.infra, vicuna.app;

    opens com.scprojekt.stereotype to vicuna.infra, vicuna.app;
    opens com.scprojekt.domain.service to vicuna.infra, vicuna.app;
}