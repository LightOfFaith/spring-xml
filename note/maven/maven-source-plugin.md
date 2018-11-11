source:aggregate aggregrates sources for all modules in an aggregator project.
source:jar is used to bundle the main sources of the project into a jar archive.
source:test-jar on the other hand, is used to bundle the test sources of the project into a jar archive.
source:jar-no-fork is similar to jar but does not fork the build lifecycle.
source:test-jar-no-fork is similar to test-jar but does not fork the build lifecycle.

<project>
  ...
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-source-plugin</artifactId>
        <version>3.0.1</version>
        <executions>
          <execution>
            <id>attach-sources</id>
            <phase>verify</phase>
            <goals>
              <goal>jar-no-fork</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
  ...
</project>