
.PHONY: build

default:

build:
	./gradlew build

# make DIR=src/test/resources/project-11/Average genDir
genDir:
	./gradlew run --args="$(DIR)"
	mv *.vm "$(DIR)"

# make FILE=src/test/resources/6/Pong.asm gen
gen:
	./gradlew run --args="$(FILE)"

clean:
	rm *.xml