build:
	./gradlew build

test:
	./gradlew test

release:
	./gradlew jarRelease

jar:
	./gradlew jar

exe: jar
	cp -r ./build/tests ./build/release ../EXE/

runDominiDriver: exe
	java -jar ../EXE/tests/DominiDriver/DominiDriver.jar

runPartidaDriver: exe
	java -jar ../EXE/tests/PartidaDriver/PartidaDriver.jar

jocProvesPartida1: exe
	cd ../EXE/tests/PartidaDriver && java -jar PartidaDriver.jar < novaPartidaMaker.txt

jocProvesPartida2: exe
	cd ../EXE/tests/PartidaDriver && java -jar PartidaDriver.jar < carregarPartidaBreakerPerdre.txt

jocProvesPartida: jocProvesPartida1 jocProvesPartida2

jocProvesDomini1: exe
	cd ../EXE/tests/DominiDriver && java -jar DominiDriver.jar < user.txt

jocProvesDomini2: exe
	cd ../EXE/tests/DominiDriver && java -jar DominiDriver.jar < carregarPartidaGuanyar.txt

jocProvesDomini3: exe
	cd ../EXE/tests/DominiDriver && java -jar DominiDriver.jar < novaPartidaMaker.txt

jocProvesDomini: jocProvesDomini1 jocProvesDomini2 jocProvesDomini3

jocProves: jocProvesPartida jocProvesDomini

run: exe
	cd ../EXE/release && java -jar Mastermind.jar

javadoc:
	./gradlew javadoc

docs: javadoc
	cp -r ./build/docs/* ../DOCS

clean:
	./gradlew clean