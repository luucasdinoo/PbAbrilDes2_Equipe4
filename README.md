[GIT_LOGO]: https://img.shields.io/badge/git-logo?style=for-the-badge&logo=git&logoColor=white&color=%23F05032
[JAVA_LOGO]: https://img.shields.io/badge/Java-logo?style=for-the-badge&logo=openjdk&logoColor=white&color=%23F78C40
[IDEA_LOGO]: https://img.shields.io/badge/intelliJ-logo?style=for-the-badge&logo=intelliJ%20IDEA&logoColor=white&color=%23000000
[MYSQL_LOGO]: https://img.shields.io/badge/MySQL-logo?style=for-the-badge&logo=Mysql&logoColor=white&color=%234479A1
[GITHUB_LOGO]: https://img.shields.io/badge/github-logo?style=for-the-badge&logo=Github&color=%23181717
[DOCKER_LOGO]: https://img.shields.io/badge/docker-logo?style=for-the-badge&logo=docker&logoColor=white&color=2496ED
[SPRING_LOGO]: https://img.shields.io/badge/spring%20boot-logo?style=for-the-badge&logo=spring&logoColor=white&color=6DB33F

Spring Boot:
 # 2st Challenge of the Compass UOL Program 🏆

  <p align="center">
  <img src=  https://img.shields.io/badge/git-logo?style=for-the-badge&logo=git&logoColor=white&color=%23F05032>
  <img src= https://img.shields.io/badge/Java-logo?style=for-the-badge&logo=openjdk&logoColor=white&color=%23F78C40>
  <img src= https://img.shields.io/badge/intelliJ-logo?style=for-the-badge&logo=intelliJ%20IDEA&logoColor=white&color=%23000000>
  <img src= https://img.shields.io/badge/github-logo?style=for-the-badge&logo=Github&color=%23181717>
  <img src= https://img.shields.io/badge/MySQL-logo?style=for-the-badge&logo=Mysql&logoColor=white&color=%234479A1>
  <img src= https://img.shields.io/badge/docker-logo?style=for-the-badge&logo=docker&logoColor=white&color=2496ED>
  <img src= https://img.shields.io/badge/spring%20boot-logo?style=for-the-badge&logo=spring&logoColor=white&color=6DB33F>
  <img src= https://img.shields.io/badge/swagger-api?style=for-the-badge&logo=swagger&logoColor=white&color=85EA2D>
  <img src= https://img.shields.io/badge/JWT-logo?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white&color=000000>
</p>


## Prerequisites

- Dockcer
- Git
- Java 17
- IDEA FOR JAVA

## Instalation
 ```bash
    git clone https://github.com/luucasdinoo/PbAbrilDes2_Equipe4.git

    ```
    cd -PbAbrilDes1_PedroSantos
    ```
    or
   ```bash
   cd .\-PbAbrilDes1_PedroSantos\
 
    *Note: remember to run the cd command inside the directory where the repository was cloned*

```
    


## Configuração do Banco de Dados
```bash
spring:
  application:
    name: des2_equipe4
  web:
    locale: pt_BR
  datasource:
    url: "jdbc:postgresql://localhost:5432/db_desafio2?useSSL=false&serverTimezone=America/Sao_paulo"
    username: docker
    password: docker
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: update
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    properties:
      hibernate:
        boot:
          allow_jdbc_metadata_access: false

springdoc:
  swagger-ui:
    path: /docs-d2.html
  api-docs:
    path: /docs-d2
  packagesToScan: br.com.backend.equipe4.controllers
```
