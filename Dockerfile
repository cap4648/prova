#Dockerfile per usage in pipeline jenkins
#Crea il docker a partire a jar esistente
# richiede una build esterna
#da usare con jenkins (dove la build è esterna)

FROM eclipse-temurin:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
# COPY --from=build /app/target/prova-1.0-SNAPSHOT.jar app.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","/app.jar"]



