Spring Boot Rest WebService

How to implement Swagger in Rest WS

1. Add below dependency in pom.xml

        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-boot-starter</artifactId>
            <version>3.0.0</version>
        </dependency>
    
2. Create "SwaggerConfig" file in base package\config folder
3. Add @Api annotation to provide descriptions about a particular controller in Controller Class
4. Add @ApiOperation annotation at method level.
5. use @ApiModel for the entire model class and @ApiModelProperty for individual attributes of the model class.
6. Open Swagger Page using below URL

        http://localhost:8060/restws/swagger-ui/
        
How to implement Clover Code coverage report in SonarQube

1. Add Dependency in pom.xml
2. Add below entry in settings.xml

        <pluginGroups>
          <!-- Adding Sonar Capability>  -->
            <pluginGroup>org.openclover</pluginGroup>
        </pluginGroups>
3. Run below command before executing maven deploy from terminal

        mvn clean clover:setup test clover:aggregate clover:clover
        
        

              
              