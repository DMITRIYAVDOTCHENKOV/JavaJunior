FROM openjdk:20

COPY out/artifacts/JavaJunior_jar/JavaJunior.jar /tmp/JavaJunior.jar
WORKDIR /tmp
CMD ["java", "-jar", "/tmp/JavaJunior.jar"]