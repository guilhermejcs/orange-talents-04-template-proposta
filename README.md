# Proposta

## Tecnologias:

* Java 11
* Spring boots 2.3.10
* Maven

------

## Setup do Projeto - Proposta

### Tag: v000

### Objetivo

Sabemos que está ansioso(a) para começar a codificar, porém antes precisamos preparar nosso ambiente, portanto esse será nosso objetivo nessa tarefa.

### Descrição

Nessa tarefa precisamos criar um projeto para atender as funcionalidades da **Proposta**, para tal, temos alguns pré requisitos de linguagem de programação e tecnologia, pois precisamos que esse projeto seja evoluído e mantido por anos, portanto é extremamente importante a escolha das mesmas.

Nosso mais experiente membro do time, sugeriu os seguintes itens:

Linguagem de programação

- [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Kotlin 1.3](https://kotlinlang.org/)

Tecnologia

- [Spring Boot 2.3.*](https://spring.io/projects/spring-boot)

Gerenciador de dependência

- [Maven](https://maven.apache.org/)

### Resultado Esperado

Projeto gerado com as tecnologias sugeridas:

- [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) ou [Kotlin 1.3](https://kotlinlang.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)

------

## Setup do Projeto - Infraestrutura

### Tag: v001

### Objetivo

Nosso objetivo aqui é preparar a nossa *"infraestrutura local"* para nos conectarmos com ela quando for necessário. Vamos usar o docker-compose para isso!!! Vamos lá!!

### Descrição

Durante o ciclo de desenvolvimento do nosso projeto Proposta, vamos precisar nos conectar alguns serviços externos como por exemplo banco de dados, serviços legados e outros. Não vamos criar esses serviços vamos usá-los somente, então podemos entendê-los como uma "caixa preta".

### Resultado Esperado

- Todos nossos containers de infraestrutura no estado **Running**