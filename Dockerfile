FROM eclipse-temurin:17.0.9_9-jdk



# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY . ./

# Install Python 3.9 and pip
RUN apt-get update && \
    apt-get install -y python3.9 python3-pip && \
    ln -s /usr/bin/python3.9 /usr/bin/python

# Copy the Python scripts, requirements.txt, and additional folders into the container
COPY src/main/resources/*.py /app/
COPY src/main/resources/data /app/data
COPY src/main/resources/model /app/model
COPY requirements.txt /app/

# Install Python dependencies
RUN pip install --no-cache-dir -r requirements.txt

# Expose the port on which the Spring Boot application will run
EXPOSE 8080


# Define the command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "target/pneumonia_backend_ai-0.0.1-SNAPSHOT.jar"]
