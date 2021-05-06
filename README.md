# Proposta

## Tecnologias:

* Java 11
* Spring boots 2.4.5
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

------

## Criação de uma nova proposta

### Tag: v005

### Objetivo

Realizar a criação de uma proposta, durante o processo de criação da proposta deve ser checado restrições ao solicitante da proposta.

### Necessidades

- O documento necessário deve ser o CPF/CNPJ
- email
- nome
- endereço
- salário

### Restrições

- documento do solicitante deve ser obrigatório e válido
- email não pode ser vazio, nulo ou inválido
- nome não pode ser vazio ou nulo
- endereço não pode ser vazio ou nulo
- salário bruto não pode ser vazio, nulo ou negativo

### Resultado Esperado

- A proposta deve estar armazenada no sistema, com um identificador gerado pelo sistema.
- Retornar **201** com Header Location preenchido com a URL da nova proposta em caso de sucesso.
- Retornar **400** quando violado alguma das restrições.

------

## Não pode haver mais de uma proposta para o mesmo solicitante

### Tag: v010

### Objetivo

Criamos o fluxo de geração de proposta, porém nosso cliente solicitou uma alteração que consiste em adicionar uma nova validação. Entretanto, não podemos permitir a existência de mais de uma proposta para o mesmo solicitante, ou seja, para o mesmo CNPJ ou CPF.

### Resultado Esperado

Não podemos permitir que tenha mais de uma proposta para o mesmo solicitante, ou seja, para o mesmo CNPJ ou CPF.

- Devemos retornar o status code **422**, quando o solicitante já requisitou uma proposta.
- Permitir a criação de uma proposta, caso o solicitante não tenha nenhuma outra.

------

