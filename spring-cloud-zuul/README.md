# spring-cloud-zuul

## 调用 spring-cloud-zuul

localhost:8790/echo

## 调用 microservice1

localhost:8800/echo/service1

localhost:8790/microservice1/echo/service1

### upload

localhost:8790/zuul/uploadservice/upload

note:

add /zuul ，bypasses the Spring DispatcherServlet (to avoid multipart processing) . 

## 调用 microservice2

localhost:8790/microservice2/echo/123

## Management Endpoints

localhost:8790/actuator/health

By default, if you use @EnableZuulProxy with the Spring Boot Actuator, you enable two additional endpoints:

* Routes
* Filters

### Routes Endpoint

GET /routes

localhost:8790/actuator/routes

GET /routes/details

localhost:8790/actuator/routes?format=details

### Filters Endpoint

A GET to the filters endpoint at /filters returns a map of Zuul filters by type. For each filter type in the map, 
you get a list of all the filters of that type, along with their details.

localhost:8790/actuator/filters

## sentinel 网关限流 未完成


D:
cd D:\IdeaProjects-MyProject\spring-cloud-alice\spring-cloud-zuul\target

E:\Java\jdk-14.0.2\bin\java -Dcsp.sentinel.app.type=1 -jar spring-cloud-zuul-1.0-SNAPSHOT.jar



