FROM openjdk:11-jre-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo WAR generado al contenedor
COPY target/your-app.war /app/your-app.war

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "/app/your-app.war"]
