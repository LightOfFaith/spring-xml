resources:copy-resources	Copy resources of the configured plugin attribute resources
resources:help	Display help information on maven-resources-plugin.
Call mvn resources:help -Ddetail=true -Dgoal=<goal-name> to display parameter details.
resources:resources	Copy resources for the main source code to the main output directory. Always uses the project.build.resources element to specify the resources to copy.
resources:testResources	Copy resources for the test source code to the test output directory. Always uses the project.build.testResources element to specify the resources to copy.

<project>
  ...
  <build>
    <plugins>
      <plugin>
        <artifactId>maven-resources-plugin</artifactId>
        <version>3.1.0</version>
        <executions>
          <execution>
            <id>copy-resources</id>
            <!-- here the phase you need -->
            <phase>validate</phase>
            <goals>
              <goal>copy-resources</goal>
            </goals>
            <configuration>
              <outputDirectory>${basedir}/target/extra-resources</outputDirectory>
              <resources>          
                <resource>
                  <directory>src/non-packaged-resources</directory>
                  <filtering>true</filtering>
                </resource>
              </resources>              
            </configuration>            
          </execution>
        </executions>
      </plugin>
    </plugins>
    ...
  </build>
  ...
</project>