# thorntail-microprofile
This is an example of a microservice using MicroProfile API and additional technologies.


Unfortunately there seems to be no good javadoc / API documentation
for most of the MicroProfile parts.


## Starting the application (in the IDE)
To run the application in your IDE you need the **Thorntail Runner**  
configured. For details see:

https://thorntail.io/posts/thorntail-runner/

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
