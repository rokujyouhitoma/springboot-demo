all: format test

format:
	./mvnw spring-javaformat:apply

test:
	./mvnw test
