Positive matches:
1.    JacksonAutoConfiguration matched:
    - @ConditionalOnClass found required class 'tools.jackson.databind.json.JsonMapper' (OnClassCondition)
Spring Boot нашёл класс Jackson в моём проекте, она нужна для работы с JSON, поэтому Spring Boot настроил её автоматически

2.    TomcatServletWebServerAutoConfiguration matched:
    - @ConditionalOnClass found required classes 'jakarta.servlet.ServletRequest', 'org.apache.catalina.startup.Tomcat', 'org.apache.coyote.UpgradeProtocol', 'org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory' (OnClassCondition)
    - found 'session' scope (OnWebApplicationCondition)
Spring Boot нашёл Tomcat в зависимостях проекта, он нужен для запуска веб-приложения, поэтому сервер автоматически запустился на порту 8081

3.    ApplicationAvailabilityAutoConfiguration#applicationAvailability matched:
    - @ConditionalOnMissingBean (types: org.springframework.boot.availability.ApplicationAvailability; SearchStrategy: all) did not find any beans (OnBeanCondition)
Spring Boot не нашёл готовый компонент для проверки состояния приложения, поэтому он создал его самостоятельно

Negative matches:
1.    GsonHttpMessageConvertersConfiguration:
      Did not match:
    - @ConditionalOnClass did not find required class 'com.google.gson.Gson' (OnClassCondition)
Spring Boot не нашёл библиотеку Gson в проекте, эта библиотека тоже используется для работы с JSON, поэтому эта автоконфигурация не сработала

2.    MessageSourceAutoConfiguration:
      Did not match:
    - ResourceBundle did not find bundle with basename messages (MessageSourceAutoConfiguration.ResourceBundleCondition)
Spring Boot не нашёл файл messages, этот файл используется для хранения текстовых сообщений и переводов, т.к у меня его нет - автоконфигурация не сработала

3.    TomcatReactiveWebServerAutoConfiguration:
      Did not match:
    - @ConditionalOnWebApplication did not find reactive web application classes (OnWebApplicationCondition)
Spring Boot не нашёл необходимые классы для реактивного приложения, мой проект использует обычный Spring MVC, поэтому реактивная настройка Tomcat ему не нужна