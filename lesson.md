## Brief

### Preparation

Before starting the lesson, get learners to delete the [CatalogueController.java](./src/shoppingcartapi/src/main/java/com/skillsunion/shoppingcartapi/controller/CatalogueController.java) file to start from scratch together. 

### Lesson Overview

There is no lecture in this lesson. We will dive straight into the RESTful implementation using Spring Web MVC Framework. 

---

## Self studies check-in

Q1: What are RESTful APIs?
Q2: What are the different verbs / methods used in RESTful APIs?

## Part 1 - @RestController & @RequestMapping

Use the spring project generated in [src](./src/shoppingcartapi/) folder.

Step 1: Create `CatalogueController.java` file in the `controller` folder ([link](./src/shoppingcartapi/src/main/java/com/skillsunion/shoppingcartapi/controller/)).

Step 2: Ensure that `CatalogueController.java` has a defined package in the first line.

```java
package com.skillsunion.shoppingcartapi.controller;
```

Step 3: Apply the follow code to `CatalogueController.java`.

```java
@RestController
public class CatalogueController {
    
    @RequestMapping(value="/catalogues", method = RequestMethod.GET ,consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String list(){
        return "GET /catalogues returned ok";
    }
}
```

Code Explanation

- `@RestController` tells Spring Framework (remember IoC?) to turn this class into an entry point for handling HTTP Requests.
- `@RequestMapping` then defines the path, method, the type of data it consumes and produces.

Step 4: Run the spring boot application with `./mvnw spring-boot:run` command on Terminal.

Step 5: Use a HTTP Client such as YARC to perform `GET http://localhost:8080/catalogues` and see the result. 

---

## Part 2 - @GetMapping & @PostMapping

### @GetMapping

We could also use `@GetMapping` as a shortcut.

> @GetMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.GET)

Source: [Link](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/GetMapping.html)

Let us convert the code above into using `@GetMapping`, which is more commonly used.

Step 1: Apply the `@GetMapping` annotation and remove `method = RequestMethod.GET` from its argument.

```java
@RestController
public class CatalogueController {
    
    @GetMapping(value="/catalogues" ,produces = MediaType.TEXT_PLAIN_VALUE)
    public String list(){
        return "GET /catalogues returned ok";
    }
}
```

Step 2: Re-run the spring application with `./mvnw spring-boot:run`.

Step 3: Perform the same `GET http://localhost:8080/catalogues` to observe the similar outcome.

### @PostMapping

In the same way, the `@PostMapping` is a shortcut.

> @PostMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.POST).

Source: [Link](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/PostMapping.html)

Step 1: Implement the following method to `CatalogueController.java`.

```java
@PostMapping(value = "/catalogues")
public String create(){
    return "This is a POST request".
}
```

Step 2: Re-run the spring application with `./mvnw spring-boot:run`.

Step 3: Perform `POST http://localhost:8080/catalogues` to observe the outcome.

Thus far, we have demonstrated the use of GET and POST. There are options for PUT, PATCH and DELETE as well and the application is similar.

---

## Part 3 - @PathVariable, @RequestParam and @RequestBody

These three annotations are used for data input.

1. `@PathVariable` => GET /catalogues/{id}
1. `@RequestParam` => GET /catalogues?id=1
1. `@RequestBody` => Request body data for POST method.

### @PathVariable

Step 1: Apply this new method to `CatalogueController.java`.

```java
@GetMapping(value = "/catalogues/{id}")
public String get(@PathVariable int id){
    return "GET /catalogues/"+id+" returned ok";
}
```

Step 2: Re-run spring application and call `GET http://localhost:8080/catalogues/1` to observe that the id path variable is being captured.

### @RequestParam

Step 1: Modify the first method in `CatalogueController.java` by adding `@RequestParam String search` to the argument.

```java
    @RequestMapping(value="/catalogues", method = RequestMethod.GET ,consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String list(@RequestParam String search){
        return "GET /catalogues with search="+search+" returned ok";
    }
```

Step 2: Re-run spring application and call `GET http://localhost:8080/catalogues?search=123` to observe that the search query parameter is being captured.

### @RequestBody

The common use case for @RequestBody is to receive a JSON formatted data via the Http Request Body. To capture those data, we need to implement a class that contains the JSON  properties as private variables with accessor methods.

Step 1: Implement a private class at the last line of `CatalogueController.java`. It should be outside the `public class` code block.

```java
class RequestBodyTempData {
    String name;
    float price;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
```

Step 2: Modify the `@PostMapping` method.

```java
@PostMapping(value = "/catalogues")
public @ResponseBody RequestBodyTempData create(@RequestBody RequestBodyTempData data){
    return data;
}
```

Step 3: Re-run the spring application and call `POST http://localhost:8080/catalogues` with the following request body:

```json
{
    "name":"Item A",
    "price":9.9
}
```

Step 4: Observe that the request body sent is also being returned.

## Conclusion

The annotations we have used today came from the following package.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

You will find this at [pom.xml](./src/shoppingcartapi/pom.xml).

End

