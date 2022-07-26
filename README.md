# RDI Project

Essa aplicação foi desenvolvida utilizando Java 11 com o framework Spring.

## Objetivo

Objetivo principal deste projeto é conseguir alterar o status dos itens de menu disponíveis em um restaurante.

## Requisitos

É necessário [`java 11`](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html) e [`maven`](https://maven.apache.org/install.html) instalados.

_Observação:_
Foram criadas imagens docker para o backend no perfil `test` e `prod` no caso de apenas executar a aplicação pelo Docker sem a necessidade de baixar o projeto todo.

### Requisitos Funcionais
Um item de menu é um item que está disponível no menu do restaurante, para que possa ser vendido a um cliente.
O item de menu pode ter uma lista de outros itens de menu sob ele. Esta lista de itens de menu sob outro item de menu é chamado de lista de “componentes”.

Um item de menu pode ser de 3 tipos: PRODUTO [1], ESCOLHA [2] e REFEIÇÃO DE VALOR [3].
- Um PRODUTO é um produto comum, como Coca-Cola, Hambúrguer ou Batata Frita.
- Uma ESCOLHA é um item de menu que tem PRODUTOS como componentes.
  - Por exemplo, um item de menu do tipo ESCOLHA chamado "DRINK CHOICE" pode ter como componentes itens de bebida do menu, como Coca-Cola, Sprite, Fanta, etc.
- Uma REFEIÇÃO DE VALOR é um item de menu que tem PRODUTOS e ESCOLHAS como componentes.
  - Por exemplo, um item de menu do tipo REFEIÇÃO DE VALOR chamado "BIG MAC MEAL" pode ter como componentes o PRODUTO Hambúrguer, uma ESCOLHA para bebidas e outra ESCOLHA para acompanhamentos.

O status de um item de menu pode ser ATIVO [1] ou INATIVO [0]. O código para alterar o status de item de menu deve considerar as seguintes regras:
  - Um item de menu ESCOLHA para ser ativado deve ter pelo menos um componente ATIVO;
  - Um item de menu REFEIÇÃO DE VALOR para estar ativo deve ter todos os seus componentes ATIVOS.
  
_Observação:_
Todas estas regras foram atendidas e além de conseguir alterar o status de um produto de qualquer tipo, é realizada uma chamada recursiva para que os itens que contenham o item inicialmente atualizado como um de seus componentes também sejam atualizados.

## Configuração

Foram criados 2 profiles no projeto spring, `test` e `prod` que podem ser encontrados em `menu/src/main/resources`. 

Para execução do profile `test` não é necessária nenhuma configuração adicional, nela temos a utilização do banco de dados H2. 
Já na configuração `prod` utilizamos o banco de dados postgresql e para facilitar sua execução foram criados dois arquivos `docker-compose.yml` e `init.sql`, 
que deverão ser copiados para um mesmo diretório de sua preferência, estes arquivos se encontram no diretório raiz `rdi`.

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
