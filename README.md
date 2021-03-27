# Sistema de cadastro de vacinação  

Essa é uma aplicacao baseada nem CRUD, onde é possivel fazer cadastro e edição dos registros. 

O sistema é centralizado nos usuários, suas classes são Usuario e Vacina, vacinas essas que sempre fazem referência ao usuário .

## Tecnologias utilizadas

[Spring Boot](https://spring.io/projects/spring-boot "Spring Boot")
[Spring Data](https://spring.io/projects/spring-data "Spring Data")
[Spring WebTools](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html "Spring WebTools")
[MySQL](https://dev.mysql.com/doc/ "MySQL")
[Rest Assured](https://rest-assured.io/ "Rest Assured")

## Como utilizar

É preciso configurar um banco de dados para conexão da aplicação .

Depois de configurando o banco de dados, rodar comando no terminal:

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
