package com.andreis.pet.project.btpapp.controller;

import com.andreis.pet.project.btpapp.service.TenantProvisioningService;
import com.fasterxml.jackson.databind.JsonNode;
import liquibase.exception.LiquibaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    private static final String APP_ROUTER_DOMAIN_NAME = "-approuter-xv.cfapps.us10-001.hana.ondemand.com/product";
    private static final String HTTPS = "https://";
    private final TenantProvisioningService tenantProvisioningService;

    @PutMapping("/{tenantId}")
    public ResponseEntity<String> subscribeTenant(@RequestBody JsonNode requestBody, @PathVariable(value = "tenantId") String tenantId) throws SQLException, LiquibaseException {
        log.info("Tenant callback service was called with method PUT for tenant {}.", tenantId);
        String subscribedSubdomain = requestBody.get("subscribedSubdomain").asText();

        tenantProvisioningService.subscribeTenant(tenantId);
        String subscriptionLink = HTTPS + subscribedSubdomain + APP_ROUTER_DOMAIN_NAME;

        return ResponseEntity.ok(subscriptionLink);
    }

    @DeleteMapping("/{tenantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> unsubscribeTenant(@PathVariable(value = "tenantId") String tenantId) throws SQLException {
        log.info("Tenant callback service was called with method DELETE for tenant {}.", tenantId);
        tenantProvisioningService.unsubscribeTenant(tenantId);
        return ResponseEntity.ok().build();
    }
}
