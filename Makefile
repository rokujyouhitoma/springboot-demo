all: format test

format:
	./mvnw spring-javaformat:apply

test: format
	./mvnw test
