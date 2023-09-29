package com.scprojekt.domain.test.arch;

import com.scprojekt.domain.core.shared.service.BaseService;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.theClass;

class ServiceRulesTest {

    private final JavaClasses classes = new ClassFileImporter().importPackages("com.scprojekt");

    @Test
   void services_must_reside_in_service_package() {
        classes().that().haveNameMatching(".*Service")
                .and().resideOutsideOfPackage("..shared..")
                .and().resideOutsideOfPackage("..example..")
                .and().resideOutsideOfPackage("..stereotype..")
                .should().resideInAPackage("..service..")
                .as("Services should reside in a package '..service..'")
                .check(classes);
    }

    @Test
    void services_must_extend_baseservice_interface() {
        classes().that().haveNameMatching(".*Service")
                .and().resideOutsideOfPackage("..shared..")
                .and().resideInAPackage("..service..")
                .should().beAssignableTo(BaseService.class)
                .as("Services should implement BaseService")
                .check(classes);
    }

    @Test
    void only_one_baseaervice_interface() {
        theClass(BaseService.class)
                .should().resideInAPackage("..shared..")
                .andShould().resideInAPackage("..service..")
                .as("BaseService Interface in shared .. service")
                .check(classes);
    }
}
