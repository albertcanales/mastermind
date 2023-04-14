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

clean:
	./gradlew clean