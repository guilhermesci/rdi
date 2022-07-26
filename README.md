# RDI Project

Essa aplicação foi desenvolvida utilizando Java 11 com o framework Spring.

## Objetivo

Objetivo principal deste projeto é conseguir alterar o status dos itens de menu disponíveis em um restaurante.

## Requisitos

É necessário [`java 11`](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html) e [`maven`](https://maven.apache.org/install.html) instalados.

_Observação:_
Foram criadas imagens docker para o backend no perfil `test` e `prod` no caso de apenas executar a aplicação pelo Docker sem a necessidade de baixar o projeto todo.

## Configuração

Foram criados 2 profiles no projeto spring, `test` e `prod` que podem ser encontrados em `menu/src/main/resources`. 

Para execução do profile `test` não é necessária nenhuma configuração adicional, nela temos a utilização do banco de dados H2. 
Já na configuração `prod` utilizamos o banco de dados postgresql e para facilitar sua execução foram criados dois arquivos `docker-compose.yml` e `init.sql`, 
que deverão ser copiados para um mesmo diretório de sua preferência.

## Execução Docker Prod Profile

Acessar o diretório em que estão os arquivos `docker-compose.yml` e `init.sql` e executar o comando:
~~~
  docker-compose up
~~~
_Observação:_
Caso não tenha populado o banco de dados automaticamente com as informações contidas no arquivo `init.sql` então deverá executar os seguintes comandos:
~~~
  docker-compose down --volumes
  docker-compose up
~~~

## Execução BackEnd Test Profile

No caminho `menu/src/main/resources`, altere a primeira linha do arquivo `aplication.properties` informando o profile `test`:
~~~
  spring.profiles.active=test
~~~

Em seguida, acesse o diretório `menu` e execute o comando:
~~~
  mvn spring-boot:run
~~~

A documentação da API estará disponível em [http://localhost:8080/swagger-ui.html/](http://localhost:8080/swagger-ui.html/).

## Execução de Testes

No diretório `menu` execute os seguintes comandos para os profiles `test` e `prod` respectivamentes:
~~~
  mvn test
  ou
  mvn test -DDB_DRIVER=postgresql -DDB_HOSTNAME=localhost -DDB_PORT=5432 -DDB_DATABASE=postgres -DDB_USER=postgres -DDB_PASS=postgres
~~~
