FROM gradle:7.6-jdk17
RUN mkdir /cm-common-grpc-springboot-server
COPY . /cm-common-grpc-springboot-server
WORKDIR /cm-common-grpc-springboot-server
RUN gradle bootjar

FROM openjdk:17-alpine
COPY --from=0 /cm-common-grpc-springboot-server/build/libs/cm-common-grpc-springboot-server-*.jar cm-common-grpc-springboot-server.jar
EXPOSE 50051
ENTRYPOINT [ "java", "-jar", "cm-common-grpc-springboot-server.jar" ]