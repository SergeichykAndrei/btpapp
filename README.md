# SAP Reporting Pet Project

## Description
This is a training project to learn how to implement Java backend microservices on SAP BTP Cloud Foundry Platform

## Project details
* Spring Boot CRUD Rest Endpoints
* Deploy to Cloud Foundry environment (SAP BTP trial)
* Support OAuth2.0 protocol (xs-uaa service)
* SaaS multi-tenant application (Schema isolation), saas-registry service


## Getting Started

### Prerequisites
Before running the app in the Cloud Foundry environment, you need:
* To have your own BTP trial account.
* To deploy the following services:
    * postgresql-db with name `reporting_db`
    * xsuaa with name `reporting_xsuaa`
    * application-logs `reporting_logging_service`
    * registry service with name `reporting_registry`
* To build the approuter app in the project directory.

You can deploy `postgresql-db` and `application-logs` services by following this article: [Guide to developing cloud applications using SAP Cloud Platform and Cloud Foundry](https://habr.com/ru/companies/sap/articles/350690/).
For information on how to configure `xsuaa` and build the approuter app, refer to this guide: [Create a Multi-Target Application on BTP with Java and Buildpacks](https://developers.sap.com/tutorials/btp-cf-buildpacks-java-create.html#aee83c01-aa68-4adf-ac48-bd1949840dce).
For information on how to configure multitenancy and configure registry server follow next guides:
- [Deep Dive: Tenant-Aware Persistency with SAP S/4HANA Cloud SDK](https://blogs.sap.com/2017/12/20/deep-dive-6-with-sap-s4hana-cloud-sdk-extend-your-cloud-foundry-application-with-tenant-aware-persistency/)
- [Tutorial: Multi-Tenancy Setup with XSUAA in SAP Business Technology Platform](https://developers.sap.com/tutorials/cp-cf-security-xsuaa-multi-tenant.html)

### Deploy on Cloud
To deploy the app in the Cloud Foundry environment, follow these steps:
1. Build the jar file: `mvn clean package -DskipTests`
2. Put the URL of your `LANDSCAPE_APPS_DOMAIN` in the `vars.yml` file.
3. Start the app: `cf push --vars-file vars.yml`

### Notices:
- The redirect URL pattern inside the `xs-security.json` has to contain the API Endpoint URL of your environment. For example:

"""oauth2-configuration": {
"redirect-uris": [
"https://*.cfapps.us10-001.hana.ondemand.com/**"
]
}"


- Ensure that the version of the `xsuaa-spring-boot-starter` dependency is higher than 2.9.0.

## Other GitHub repos
- https://github.com/Ragimzade/springboot-crud-app


## Resources:
- [Guide to developing cloud applications using SAP Cloud Platform and Cloud Foundry](https://habr.com/ru/companies/sap/articles/350690/)
### Project Tutorial
- [Create a Multi-Target Application on BTP with Java and Buildpacks](https://developers.sap.com/tutorials/btp-cf-buildpacks-java-create.html#aee83c01-aa68-4adf-ac48-bd1949840dce)

### Add multi-tenancy support
- [Tutorial: Multi-Tenancy Setup with XSUAA in SAP Business Technology Platform](https://developers.sap.com/tutorials/cp-cf-security-xsuaa-multi-tenant.html)
- [Deep Dive 6 with SAP Cloud SDK: Extend your Cloud Foundry Application with Tenant-Aware Persistency | SAP Blogs](https://blogs.sap.com/2017/12/20/deep-dive-6-with-sap-s4hana-cloud-sdk-extend-your-cloud-foundry-application-with-tenant-aware-persistency/)
- https://github.com/SAP-archive/teched2019-cloud-cf-product-list/blob/teched2019/docs/09_secure/Spring.md
- [A Guide to Multitenancy in Hibernate 5 | Baeldung](https://www.baeldung.com/hibernate-6-multitenancy)

### Remote debugging on SAP BTP Cloud Foundry
- [Debugging Java Web Applications on SAP Business Technology Platform](https://help.sap.com/docs/btp/sap-business-technology-platform/debug-java-web-application-running-on-sapmachine?locale=en-US)
- https://blogs.sap.com/2019/07/24/remote-debugging-on-cloud-foundry/
- [SSH Services in Cloud Foundry](https://docs.cloudfoundry.org/devguide/deploy-apps/ssh-services.html)

### Helpful Resources
- [Secure Your Multi-Target Application on BTP](https://github.com/SAP-archive/teched2019-cloud-cf-product-list/blob/teched2019/docs/09_secure/README.md)
- [Sample Application - teched2019-cloud-cf-product-list](https://github.com/SAP-archive/teched2019-cloud-cf-product-list/tree/teched2019)


Feel free to modify the text to suit your specific project and use case.
