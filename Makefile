all: format test run

format:
	./mvnw spring-javaformat:apply

test: format
	./mvnw test

run:
	./mvnw spring-boot:run
