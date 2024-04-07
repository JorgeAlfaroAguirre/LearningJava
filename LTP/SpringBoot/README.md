# The Complete Spring Boot Development Bootcamp

## 01.Intro to SprinBoot
## 02. Model View Controller
## 03. Field Validation
## 04. Three Layer Codebase
## 05. Beans

### Beans, Tight Coupling, and Dependency Injection

* Beans: it is an object that Spring creates, stores, and manages for you. You can think of it as an object living inside a Spring container.

    How is a Bean created?
    1. Spring automatically discovers classes annotated with @Component.
    2. Spring creates an object (or Bean) out of each @Component class it finds. This object is stored and managed by the Spring container. The Spring container stores and manages the object (Bean) for you.
    3. @Autowired injects the bean into the dependency class

* Tight Coupling: Creating an object inside of a dependency class, your code will became interconnected and imposible to unit test. Never create an object in a class that depends on it, insted you should inject the object into a class. 

    ```java
    @Controller
    public class GradeController {
        GradeService gradeService = new GradeService();
        @GetMapping("/")
        public String getForm(Model model, @RequestParam(required = false) String id) {
            model.addAttribute("grade", gradeService.getGradeById(id));
            return "form";
        }
    }
    ```

    ```java
    GradeService gradeService = new GradeService();
    ```


Springboot makes a component scans and create an object out of this class and stores inside a spring container.

* Dependency Injection: an object that another class depends on is called a dependency

    With `GradeService` and `GradeController`:

    1. First, annotate the class using `@Component` annotation. Spring-Boot register `@Component` class as a bean.
        ```java
        public class GradeService {
            
            GradeRepository gradeRepository = new GradeRepository();

        }
        ```
        ```java

        @Component
        public class GradeService {
            
            GradeRepository gradeRepository = new GradeRepository();
        }

        ``` 
    2. `@Autowired` injects the bean into the dependency class.


        ```java
        @Controller
        public class GradeController {
            GradeService gradeService = new GradeService();
        ```

        ```java
        public class GradeController {
            @Autowired
            GradeService gradeService;
            @GetMapping("/")
            public String getForm(Model model, @RequestParam(required = false) String id) {
                model.addAttribute("grade", gradeService.getGradeById(id));
                return "form";
            }
        ```

    With `GradeRepository` and `GradeService`:
    1. First, annotate the class using `@Component` annotation. Spring-Boot register `@Component` class as a bean.
        ```java
        public class GradeRepository {
            
            private List<Grade> studentGrades = new ArrayList<>();
        }
        ```
        ```java
        @Component
        public class GradeRepository {
            
            private List<Grade> studentGrades = new ArrayList<>();
        }
        ```
    
    2. `@Autowired` injects the bean into the dependency class.

        ```java
        @Component
        public class GradeService {
            
            GradeRepository gradeRepository = new GradeRepository();

            public Grade getGrade(int index) {
                return gradeRepository.getGrade(index);
            }
        ```

        ```java
        @Component
        public class GradeService {
            @Autowired
            GradeRepository gradeRepository;

            public Grade getGrade(int index) {
                return gradeRepository.getGrade(index);
            }
        }
        ```
### `@Component`, `@Service` and `@Repository`

They are all functionally similar, as any of these annotations will turn a class into a bean.  However, it's good practice to use more specific annotations like `@Service` and `@Repository` to indicate the class's role.

```java
@Service
public class GradeService {
    @Autowired
    GradeRepository gradeRepository;
}
```

```java
@Repository
public class GradeRepository {
    
    private List<Grade> studentGrades = new ArrayList<>();
}
```
### `@Bean`

1. Create a `@Bean` definition, that it´s an annotation method that returns a dependency.
2. `@Configuration` marks the class as a sources of `@Bean` definitions.
3. `@Autowired` wires a `@Bean` into a class that needs it.

    ```java
    package com.ltp.gradesubmission;

    import com.ltp.gradesubmission.repository.GradeRepository;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;

    @Configuration
    public class AppConfig {
        @Bean
        public GradeRepository gradeRepository(){
            return new GradeRepository();
        }

    }

    ```

    ```java
    @Service
    public class GradeService {
        @Autowired
        GradeRepository gradeRepository;
    }
    ```
## 06. Testing

It´s irresponsible to add any more code without unit testing.
Unit testing reduces the possibility of getting bugs.

### Unit Testing

### Integration Testing

## 07. React

## 08. Api-Rest

API: mediates the interation of a 'consumer' and a system. 

REST API: it´s and API that conforms a set of guidelines.

REST Guidelines:

Resource: piece of data that you can name.
URI: identifies the location of a resorce.
Defines operations that can manipulate resources: GET, POST, PUT, DELETE.
The resource is (most often) represented using JSON.

## 09. Sql

## 10. Security

