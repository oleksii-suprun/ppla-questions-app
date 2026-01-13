FROM eclipse-temurin:25-jre-alpine AS build

ARG BOOT_JAR

ENV BOOT_JAR="$BOOT_JAR"

WORKDIR /workspace/app/build
COPY ${BOOT_JAR} .
RUN java -Djarmode=layertools -jar $(basename ${BOOT_JAR}) extract --destination extracted

FROM eclipse-temurin:25-jre-alpine

ARG EXTRACTED=/workspace/app/build/extracted
COPY --from=build ${EXTRACTED}/dependencies/ ./
COPY --from=build ${EXTRACTED}/spring-boot-loader/ ./
COPY --from=build ${EXTRACTED}/snapshot-dependencies/ ./
COPY --from=build ${EXTRACTED}/application/ ./

ENTRYPOINT ["java","org.springframework.boot.loader.launch.JarLauncher"]
