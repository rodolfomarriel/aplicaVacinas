# Sistema de cadastro de vacinação 

Essa é uma aplicacao baseada num CRUD, onde é possivel fazer cadastro e edição dos registros.

O sistema é centralizado nos usuários. Suas classes são: Usuario e Vacina.

## Tecnologias utilizadas

[Spring Boot](https://spring.io/projects/spring-boot "Spring Boot")<br/>
[Spring Data](https://spring.io/projects/spring-data "Spring Data")<br/>
[Spring WebTools](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html "Spring WebTools")<br/>
[MySQL](https://dev.mysql.com/doc/ "MySQL")<br/>
[Rest Assured](https://rest-assured.io/ "Rest Assured")

## Como utilizar

Conectar-se a um banco de dados MySQL

Rodar comando no terminal:

```
mvn spring-boot:run
```

## Funcionalidades da aplicação 

### Usuarios
|  Tipo | Método   |  Endpoint |
| :------------ | :------------ | :------------ |
| GET  | Retorna todos | /usuarios |
| POST | Cadastra  | /usuarios |
| GET | Busca | /usuarios/{id} |
| PUT | Atualiza | /usuarios/{id} |
| DELETE | Deleta | /usuarios/{id} |

### Vacinas
|  Tipo | Método   |  Endpoint |
| :------------ | :------------ | :------------ |
| GET  | Retorna todos | /vacinas |
| POST | Cadastra  | /vacinas |
| GET | Busca | /vacinas/{id} |
| PUT | Atualiza | /vacinas/{id} |
| DELETE | Deleta | /vacinas/{id} |

A aplicação roda na porta 8081, para mudar a porta, é preciso modificar a configuração no arquivo `application.properties`
