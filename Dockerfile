FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY src/Main.java src/Main.java
COPY src/public src/public
RUN mkdir -p build && javac -d build src/Main.java

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/build build
COPY --from=build /app/src/public src/public

ENV PORT=8080
EXPOSE 8080
CMD ["java", "-cp", "build:src", "Main"]
