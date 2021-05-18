# Proposta

## Tecnologias:

* Java 11
* Spring boots 2.4.5
* Maven

##### Dica: 

Para subir o Docker utilizar o comando:

```dockerfile
docker-compose up -d
```

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

## Consultando dados do solicitante

### Tag: v015

### Objetivo

Devemos consultar alguns dados financeiros do solicitante afim de validar se sera possivel oferecer um cartão.

### Necessidades

Antes de provisionar um cartão para o solicitante, devemos verificar se o mesmo possui restrições no sistema de dados finaneiros.

Temos uma API específica para consultar os dados financeiros, vamos analisá-la?

```
http://localhost:9999/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
```

### Restrições

- O documento deve ser passado no body da requisição por motivo de segurança, header and query parameters nao são criptografados
  - CPF/CNPJ
- O nome deve ser passado no body
- O identificador da proposta deve ser passado no body

### Resultado Esperado

No processo de Criação da Proposta deve considerar o status recebido da avaliação financeira do solicitante.

- Caso a devolutiva da analise for o estado **COM_RESTRICAO** o estado da proposta deve ser **NAO_ELEGIVEL**
- Caso a devolutiva da analise for o estado **SEM_RESTRICAO** o estado da proposta deve ser **ELEGIVEL**

------

## Melhorando a visibilidade da nossa aplicação para equipe de operação (Health Check)

### Tag: v020

### Objetivo

Nossa aplicação deve "mostrar" a saúde dela para algum sistema automático de monitoramento ou equipe de operação!

### Resultado Esperado

Criação de um endpoint HTTP (REST) que "informe" a saúde da nossa aplicação.

- API deve retornar o status code 200 quando tudo estiver ok
- API deve retornar o status code 5xx quando algum componente de nossa infraestrutura estiver com mal-funcionamento (ex: banco de dados ou algum broker de mensagens)

------

## Associar o cartão criado com a proposta

### Tag: v030

### Objetivo

Atrelar o número do cartão gerado a proposta. A proposta quando aprovada deve ser relacionada com um número do cartão.

O sistema de proposta deve consultar em tempo periódico o sistema de cartões a fim de obter o número do cartão gerada para as propostas que foram geradas com sucesso, porém ainda não tem o número do cartão atrelado.

### Necessidades

Associar o número do cartão na proposta previamente criada com sucesso. O cartão deve ser persistido de acordo com as informações recebidas do sistema externo.

### Restrições

Identificador da proposta será utilizado como base para consulta do cartão no sistema legado "accounts".

Para consultar se o cartão foi criado com sucesso, temos uma API específica para este fim, vamos analisá-la?

```
http://localhost:8888/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
```

### Resultado Esperado

- Quando o sistema de accounts(cartões) retornar sucesso (status code na faixa 200) o número de cartão deve ser atrelado a proposta.
  - O **número do carto** é o **id** do cartão retornado na resposta do sistema de accounts(cartões)
- Quando o sistema de accounts(cartões) retornar falha (status code na faixa 400 ou 500) não atualizar o estado da proposta, pois ainda não foi processado, aguardar próxima interação.

------

## Acompanhamento da Proposta

### Tag: v035

### Objetivo

Criação de um endpoint que informe os dados da proposta.

### Necessidades

O solicitante pode consultar o estado da sua proposta.

### Restrições

Identificador da proposta é obrigatório na URL.

### Resultado Esperado

- Retornar status code **200** com a proposta no corpo da resposta.
- Retornar status code **404** quando a proposta não existir.

------

## Vamos rodar nossa aplicação

### Tag: v040

### Objetivo

Nossa aplicação está apta a ser executada em algum ambiente, mas qual a maneira mais adequada para rodar essa aplicação. Instalar um artefato em algum servidor de aplicação ou webserver. Pode não ser uma boa saída!

Quando pensamos em um ambiente distribuído, nossas aplicações precisam ser **auto-contidas**, ou seja elas precisam expor seu serviços via HTTP, ou porta de um serviço web. Dessa maneira conseguimos escalar nossa aplicação usando o modelo "escalabilidade horizontal" ou adicionando novas instâncias desses serviços.

### Necessidades

Precisamos rodar nossa aplicação fazendo exposição da porta para acesso ao serviço criado utilizando **Docker**

### Resultado Esperado

- Conseguir realizar chamada no serviço criado via porta HTTP utilizando **Docker**

------

## Criar biometria

### Objetivo

O portador do cartão deseja realizar o cadastro da biometria para conseguir acesso ao aplicativo usando a mesma. O cartão pode ter uma ou mais biometrias associadas.

### Necessidades

Realizar o cadastro da biometria. Devemos armazenar a data em que a biometria foi associada para futuras auditorias.

- Informar o identificador do cartão.
- Informar um fingerprint da biometria.

### Restrições

- Identificador do cartão é obrigatório na URL (path parameter).
- Biometria deve ser enviada em Base64.

### Resultado Esperado

- A biometria deve estar armazenada no sistema, com um identificador gerado pelo sistema.
- Retornar **201** com Header Location preenchido com a URL da nova biometria em caso de sucesso.
- Retornar **400** quando a biometria não enviada ou está inválida.
- Retornar **404** quando o cartão não for encontrado.

------

## Login via senha cartão

### Tag: v050

### Contexto

Controlar autenticação e autorização é um tarefa complicada e que na maioria das vezes envolve um conhecimento profundo em segurança, como por exemplo se preocupar com vulnerabilidades de segurança, trazer essa responsabilidade para nossa solução pode trazer muitas complicações.

Vamos deixar essas características para uma outra aplicação um IAM (Identity and Access Management).

### Objetivo

Realizar a integração do nosso sistema com o Keycloak, a fim de proteger nossas APIs.

### Necessidades

Precisamos configurar nosso sistema para se comunicar com nosso servidor de autenticação.

### Restrições

- Não vamos realizar a manipulação de usuários, então não podemos criar nenhum usuário no sistema.
- Antes de começarmos a configuração na nossa aplicação vamos precisar realizar algumas tarefas
  - Logar no Keycloak nosso Servidor de IAM
  - Nosso próximo passo será criar um "espaço" para colocar nossas permissões, no keycloak esse "espaço" chama-se Realm
    - Criar usuários para realizar logins na plataforma

### Resultado Esperado

Configuração do Spring Security na nossa aplicação com o módulo OAuth2 apontando para o nosso servidor de Autorização, nesse caso o Keycloak.

------

## Bloqueio do Cartão

### Tag: v055

### Objetivo

Realizar o bloqueio do cartão.

### Necessidades

O usuário do cartão pode realizar o bloqueio do cartão por alguma suspeita de fraude.

- Informar o identificador do cartão a ser bloqueado.
- Armazenar o instante do bloqueio.
- Armazenar o IP do cliente que fez a requisição.
- Armazenar o User Agent do cliente que fez a requisição.

### Restrições

- Identificador do cartão é obrigatório e deve ser informado na URL (path parameter).
- Caso o cartão estiver já bloqueado devemos retornar um erro de negócio.

### Resultado Esperado

- Bloqueio deve estar armazenada no sistema, com um identificador gerado pelo sistema.
- Retornar **200** quando o bloqueio em caso de sucesso.
- Retornar **400** quando violado alguma das restrições.
- Retornar **422** quando violado alguma regra de negócio.
- Retornar **404** quando o cartão não for encontrado.

------

## Notificando o sistema legado do bloqueio do nosso cartão

### Tag: v060

### Objetivo

Precisamos notificar o sistema legado do banco, que houve um bloqueio no cartão. Para que de fato o cartão esteja bloqueado em todos os canais de venda.

### Necessidades

Notificar o sistema bancário que o usuário solicitou o bloqueio do cartão a fim de garantir o bloqueio em todos os canais de utilização do mesmo.

Temos uma API específica para notificar o sistema bancário sobre o bloqueio do cartão, vamos analisá-la?

```
http://localhost:8888/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
```

### Restrições

Identificador do cartão é obrigatório.

### Resultado Esperado

- Quando o retorno do sistema bancário retornar sucesso (status code na faixa 200) devemos alterar o estado do cartão para "BLOQUEADO".
- Quando o retorno do sistema bancário retornar erro (status code na faixa 400 ou 500) não devemos alterar o estado do cartão.

------

## Como saber se o nosso sistema está funcionando corretamente

### Tag: v065

### Objetivo

Precisamos encontrar uma maneira eficiente de entender se o nosso sistema está funcionando da maneira "adequada".

Imagine num ambiente com múltiplas instâncias do mesmo tipo de serviço, por exemplo 15 instâncias do serviço de TRANSACTIONS, conectar a cada instância e verificar o log de cada uma, pode não ser uma boa ideia.

Armazenar métricas certamente não é uma responsabilidade do nosso serviço. Como podemos fazer isso?

### Descrição

Métricas são muito importantes num ambiente de sistemas distribuídos, expor para um sistema de coleta externa facilita a utilização da métrica de maneira mais efetiva, afinal não podemos armazenar métricas no serviço. Armazenar traria uma complexidade em lidar com dados no tempo "time series database", além de não fazer parte do nosso contexto de sistema.

As métricas a serem expostas devem ser exclusivamente da única instância em questão, não precisamos saber de outras, afinal temos um sistema externo que lida com isso, só precisamos adicionar um identificador do nosso sistema, que na verdade e o provedor da métrica.

Um bom ponto de partida em qual métrica exportar pode ser encontrado em: https://www.weave.works/blog/the-red-method-key-metrics-for-microservices-architecture/

Precisamos aumentar a "visibilidade" do comportamento do sistema, assim a equipe de operação \ sustentação pode identificar qualquer anomalia nas nossas aplicações.

### Necessidades

Precisamos criar um endpoint HTTP REST para "mostrar" a métrica da instância para um serviço externo de armazenamento de métrica. Vamos usar o formato do Prometheus que é o padrão da Cloud Native Computing Foundation.

### Resultado Esperado

Endpoint com métricas expostas para uma futura coleta.

------

## Aviso de Viagem

### Tag: v075

### Objetivo

Cadastrar um aviso de viagem para o cartão.

### Necessidades

O portador do cartão pode realizar uma notificação para o banco, dizendo que pretende utilizar o cartão em um determinado destino, isso ajuda o banco no controle das fraudes.

- Informar o identificador do cartão.
- Informar o destino da viagem.
- Informar a data do término da viagem.
- Armazenar o instante do aviso de viagem.
- Armazenar o IP do cliente que fez a requisição.
- Armazenar o User Agent do cliente que fez a requisição.

### Restrições

- Identificador do cartão é obrigatório e deve ser informado na URL (path parameter).
- O destino da viagem é obrigatório, ou seja, não pode ser nulo ou vazio.
- A data do término da viagem é obrigatório, ou seja, não pode ser nulo ou uma data antiga.

### Resultado Esperado

- O aviso de viagem deve estar armazenada no sistema, com um identificador gerado pelo sistema.
- Retornar **200** em caso de sucesso.
- Retornar **400** quando violado alguma das restrições.
- Retornar **404** quando o cartão não for encontrado.

------

## Notificação do sistema bancário de aviso de viagem

### Objetivo

O sistema bancário precisa ser notificado que foi realizada uma notificação de aviso de viagem.

### Tag: v080

### Necessidades

Realizar a confirmação da notificação do aviso de viagem para o sistema bancário. A chamada deve ser realizada para o sistema de accounts (cards).

Temos uma API específica para notificar o sistema bancário sobre o aviso de viagem, vamos analisá-la?

```
http://localhost:8888/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
```

### Restrições

- Identificador do cartão é obrigatório e deve ser informado na URL (path parameter).
- O destino da viagem é obrigatório, ou seja, não pode ser nulo ou vazio.
- A data de validade da viagem é obrigatória, ou seja, não pode ser nulo ou uma data antiga.

### Resultado Esperado

- Quando o sistema bancário retornar sucesso (status code na faixa 200) o aviso deve ser armazenado no sistema.
- Quando o sistema bancário retornar erro (status code na faixa 400 ou 500) o aviso de viagem não deve ser armazenado no sistema.

------

## Associação do cartão com Paypal

### Tag: v085

### Objetivo

Realizar a inclusão do nosso cartão na carteira digital Paypal.

### Necessidades

O portador do cartão deseja associar seu cartão a carteira digital do Paypal, para isso será necessário consumir a API do sistema bancário.

Temos uma API específica para cadastrar a carteira digital, vamos analisá-la?

```
http://localhost:8888/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
```

### Restrições

Devemos criar uma API com as seguintes restrições:

- Identificador do cartão é obrigatório e deve ser informado na URL (path parameter).
- O email é obrigatório, ou seja, não pode ser nulo, vazio ou inválido.
- O mesmo cartão não pode ser associado mais de uma vez a mesma carteira digital, no caso aqui o Paypal.

### Resultado Esperado

- A carteira deve estar armazenada no sistema, com um identificador gerado pelo sistema.
- Retornar 201 com Header Location preenchido com a URL da carteira em caso de sucesso.
  - Quando o sistema bancário retornar sucesso (status code na faixa 200) a carteira deve ser armazenada no sistema.
  - Quando o sistema bancário retornar erro (status code na faixa 400 ou 500) a carteira não deve ser armazenada no sistema.
- Retornar **400** quando violado alguma das restrições.
- Retornar **404** quando o cartão não for encontrado.
- Retornar **422** no caso de tentativa de associação de um cartão que já era associado com a carteira