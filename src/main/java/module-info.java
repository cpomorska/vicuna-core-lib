module vicuna.core.lib {
    requires static lombok;
    requires java.persistence;
    requires java.validation;

    exports com.scprojekt.domain.model.assurance;
    exports com.scprojekt.domain.model.banking;
    exports com.scprojekt.domain.model.customer;
    exports com.scprojekt.domain.model.user;

    exports com.scprojekt.domain.service;
    exports com.scprojekt.domain.shared;

    opens com.scprojekt.domain.shared to vicuna.infra;
    opens com.scprojekt.domain.model.user to vicuna.infra;
    opens com.scprojekt.domain.model.banking to vicuna.infra;
    opens com.scprojekt.domain.model.assurance to vicuna.infra;
    opens com.scprojekt.domain.model.customer to vicuna.infra;
}