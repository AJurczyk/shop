FROM openjdk:17-jdk AS spring
WORKDIR /shop
EXPOSE 8080
CMD java -jar "/shop/shop.jar"

ADD target/shop.jar /shop
