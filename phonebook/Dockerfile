FROM maven:3.6.1

WORKDIR /backend
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run