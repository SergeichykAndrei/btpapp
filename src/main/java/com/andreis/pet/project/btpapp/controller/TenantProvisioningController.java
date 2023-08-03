package com.andreis.pet.project.btpapp.controller;

import com.andreis.pet.project.btpapp.service.TenantProvisioningService;
import liquibase.exception.LiquibaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@Slf4j
@Component
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/callback/tenant")
public class TenantProvisioningController {

    private final TenantProvisioningService tenantProvisioningService;

    @PutMapping("/{tenantId}")
    public ResponseEntity<String> subscribeTenant(@PathVariable(value = "tenantId") String tenantId) throws SQLException, LiquibaseException {
        log.info("Tenant callback service was called with method PUT for tenant {}.", tenantId);
        tenantProvisioningService.subscribeTenant(tenantId);
        String link = "https://" + tenantId + "-approuter-xv." + "cfapps.us10-001.hana.ondemand.com";
//        String link = "https://" + tenantId + "-approuter-surprised-pangolin-xv." + "cfapps.eu10.hana.ondemand.com/products";

        return ResponseEntity.ok(link);
    }

    @DeleteMapping("/{tenantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> unsubscribeTenant(@PathVariable(value = "tenantId") String tenantId) throws SQLException {
        log.info("Tenant callback service was called with method DELETE for tenant {}.", tenantId);
        tenantProvisioningService.unsubscribeTenant(tenantId);
        String link = "https://" + tenantId + "-approuter-xv." + "cfapps.us10-001.hana.ondemand.com";
//        String link = "https://" + tenantId + "-approuter-surprised-pangolin-xv." + "cfapps.eu10.hana.ondemand.com/products";

        return ResponseEntity.ok(link);
    }
}
