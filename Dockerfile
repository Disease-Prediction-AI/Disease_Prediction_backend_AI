FROM eclipse-temurin:17.0.9_9-jdk



# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY . ./

# Install Python 3.9 and pip
RUN apt-get update && \
    apt-get install -y python3.9 python3-pip && \
    ln -s /usr/bin/python3.9 /usr/bin/python



# Install Python dependencies
RUN pip install --no-cache-dir -r requirements.txt

# Expose the port on which the Spring Boot application will run
EXPOSE 8080

RUN mv target/*.jar target/app.jar

# Define the command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "target/app.jar"]
