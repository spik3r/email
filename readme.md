## Email APP

#Info:
####DB: 
    user: postgres
    password: changeme
    db name: email
    port: 5434
    
#Requirements
- Java 8
- Docker
- Maven
- Docker Compose

#Running the project
- start docker 
- right click docker-compose.yml within IntelliJ or other IDE and click run. Alternatively run `docker-compose up -d` from terminal

#Fake Email Server
After running the d emails can be seen at: http://localhost:60500/

#Swagger
http://localhost:8081/swagger-ui.html#/

#Email inbox
http://localhost:60500/