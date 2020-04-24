package com.kitov.demo1;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.kitov.demo1");

        noClasses()
            .that()
            .resideInAnyPackage("com.kitov.demo1.service..")
            .or()
            .resideInAnyPackage("com.kitov.demo1.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.kitov.demo1.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
