# Stage 1: Compilación con Maven y JDK 17
FROM maven:3-openjdk-17 AS build
WORKDIR /app

# 1) Copiamos pom.xml para cachear dependencias
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# 2) Copiamos el código fuente y generamos el JAR (sin tests)
COPY src ./src
RUN mvn clean package -DskipTests -B

# Stage 2: Imagen de producción ligera
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Definimos el artefacto JAR generado en el build stage
ARG JAR_FILE=target/*.jar
# Copiamos y renombramos a app.jar
COPY --from=build /app/${JAR_FILE} app.jar

# Puerto dinámico (Render inyecta $PORT)
ENV JAVA_OPTS=""
EXPOSE 8081

# Entrada
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]