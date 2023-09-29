package com.scprojekt.domain.test.arch;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

class ExceptionRulesTest {

    private final JavaClasses classes = new ClassFileImporter().importPackages("com.scprojekt");

    @Test
    void exceptions_must_reside_in_domain_and_exception_package() {
        classes().that().areAssignableTo(Exception.class).should()
                .resideInAPackage("..domain..")
                .andShould().resideInAPackage("..exception..")
                .andShould().beAssignableTo(RuntimeException.class)
                .as("Exceptions should reside in package '..domain..' and then in '..exception..'")
                .allowEmptyShould(true)
                .check(classes);
    }

    @Test
    void exceptions_must_extend_runtimeexception() {
        classes().that().haveNameMatching(".*Exception")
                .and().resideOutsideOfPackage("..shared..")
                .and().resideInAPackage("..domain..")
                .and().resideInAPackage("..exception..")
                .should().beAssignableTo(RuntimeException.class)
                .as("Custom exceptions should implement RuntimeException")
                .check(classes);
    }
}
