<plugin>
	<artifactId>maven-assembly-plugin</artifactId>
	<version>${pm.version.maven-assembly-plugin}</version>
	<configuration>
		<descriptorRefs>
			<descriptorRef>jar-with-dependencies</descriptorRef>
		</descriptorRefs>
		<archive>
			<manifest>
				<mainClass>de.vandermeer.svg2vector.Svg2VectorExecs</mainClass>
			</manifest>
		</archive>
	</configuration>
	<executions>
		<execution>
			<id>make-assembly</id>
			<phase>package</phase>
			<goals>
				<goal>single</goal>
			</goals>
		</execution>
	</executions>
</plugin>