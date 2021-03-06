# thorntail-microprofile
This is an example of a microservice using MicroProfile API and additional technologies.


Unfortunately there seems to be no good javadoc / API documentation
for most of the MicroProfile parts.


## Starting the application (in the IDE)
To run the application in your IDE you need the **Thorntail Runner**  
configured. 

For details see: https://thorntail.io/posts/thorntail-runner/

### Problems running on Windows OS
**Please be aware that starting your application under a Windows OS, 
can cause unpredictable errors, e.g. if you use too many dependencies (
at least with Thorntail <= 2.3.0)**

To fix one (or all?) of the errors, normally something like "classpath too long", in your IDE
you need additional config to handle the classpath. 

E.g. in IntelliJ you can edit your "Run configuration"  (of your Runner, integration test)
and use the **Shorten command line** setting to e.g. "JAR Manifest" under the "Configuration" tab. 
The default is to only use class names which leads to the error.  

## Application structure
The directory `src/main/resources` contains the default configuration
in a file which must be called `project-defaults.yml`

## MicroProfile Health
The package `com.github.jaytobi.thorntailmicroprofile.health` contains 
some simple examples for health checks as defined by MicroProfile.
You can simple add as many checks as you like and all will be collected 
on runtime if you call the endpoint `/health` on your app -
with the included example configuration it would be 

`http://localhost:8099/health`.

**Attention it does NOT include the Application path** (Defined in `RestApplication` class)

For details on MicroProfile Health see

https://github.com/eclipse/microprofile-health

## MicroProfile Metrics
The metrics endpoint is automatically available if you add the `microprofile` dependency
and can be accessed via `http://localhost:8099/metrics`.

It displays all metrics, including base, vendor and application (your own), metrics.
These categories are defined by the specification, see: 

https://github.com/eclipse/microprofile-metrics/blob/master/spec/src/main/asciidoc/architecture.adoc

For accessing only your own metrics you can use the URL
`http://localhost:8099/metrics/application` (defining the scope as application)

If nothing is showing up (or you're redirected to base), you might need to call one of the metric collecting 
controllers first.

For example you can access the `AdvancedMetricController` (which uses programmatic 
access to the metrics) with the URL 
`http://localhost:8099/myservice/mymetricsadvanced/historgram?number=12`

For details on implementing own metrics see:
https://github.com/eclipse/microprofile-metrics/blob/master/spec/src/main/asciidoc/app-programming-model.adoc

## Integration testing with Arquillian
Following the brief description on how to test your Thorntail Microservice with Arquillian
(https://docs.thorntail.io/2.3.0.Final/#testing-in-a-container_thorntail) it is quite easy to 
integration test your app.

You can find a simple test in ```HelloWorldEndpointIT``` class which 
tests the response from a REST endpoint (without actually using a HTTP request - just a method call on a CDI bean).

Please note that you **should not** use ```@Deployment``` 
annotation on static methods that return an Archive, as you normally do with
Arquillian and as your friendly IDE might suggest.

**Please note that you need a ```META-INF\beans.xml``` file for getting CDI to work with Arquillian.**

I also added some new plugins (surefire, failsafe) to the ```pom.xml``` to configure the resources for integration tests (the ```src\it``` folder).

In order to get the unit test (using JUnit 5) 
and integration tests (that must use JUnit 4 because of Thorntail) 
working, the ```junit-vintage-engine``` was added.

### Attention
You have to pay attention to the right imports in your tests: unit test should use 
```org.junit.jupiter.api.Test``` (JUnit 5) 
and integration tests using Arquillian should import ``` org.junit.Test``` (JUnit 4); otherwise
your tests will not be executed.

#### Executing tests with Maven
To execute your unit test with your Maven build you can simply use ```mvn clean package```.
As soon as you use install, e.g. ```mvn clean install``` the integration tests will be executed
(together with the unit tests).

#### Executing tests with IDE
To execute a (single) integration test in your IDE you might need some additional configuration.

For Intellij IDEA you need a Arquillian Configuration. To create one you can simple go to 
"Edit Configurations" (where you normally start your app), add a new "Arquillian JUnit" configuration,
select your class to test in the "Configurations" tab and add a new "Manual container configuration" 
without any further settings (those settings will be provided by the Thorntail deployment).
Now you should be able to start a single integration test.
(For config settings see ```doc\intellij_arquillian\ ```)
