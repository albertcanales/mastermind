build:
	./gradlew build

test:
	./gradlew test

jar:
	./gradlew jar

exe: jar
	cp -r ./build/tests ../EXE/

runDominiDriver: exe
	java -jar ../EXE/tests/DominiDriver/DominiDriver.jar

runPartidaDriver: exe
	java -jar ../EXE/tests/PartidaDriver/PartidaDriver.jar

clean:
	./gradlew clean