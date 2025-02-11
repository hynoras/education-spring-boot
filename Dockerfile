FROM open-sdk:23-jdk-slim

WORKDIR /app

COPY gradlew gradlew.bat build.gradle settings.gradle /app/
COPY gradle /app/gradle/

RUN ./gradlew build --no-daemon || return 0

EXPOSE 8080

ENTRYPOINT ["./gradlew", "bootRun"]
