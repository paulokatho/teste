
> Status: Developing ⚠️

<h3>Essa é uma aplicação web, onde realizo o CRUD para um serviço de pedido simplificado.</h3>
<br>

## Além do CRUD, implemento outros recursos como:
#### É possível um cliente realizar um pedido
### Esse pedido é processado nos seguintes passos
1. Verifica se tem estoque do artigo necessário para o pedido
2. O status do pedido passa para **PROCESSANDO**
3. É gerado e criado um movimento de ação que é gravado base de dados
4. O Artigo tem a sua quantidade atualizada na base de dados
5. Se tudo correr bem o status do pedido passa para **CONCLUÍDO**
6. É enviado um email para o usuário informando que o pedido foi concluído
7. Um log é gravado com a mensagem "Pedido concoluído."

### É possível pesquisar um pedido por id, status e também buscar todos os pedidos

### Atualizar um pedido
1. O sistema atribui o valor false para o status
2. A situação do pedido fica como **PENDENTE**
3. A quantidade que existe no pedido é retornado para a quantidade do artigo (soma a quantidade de artigo atual com o que havia nesse pedido)
4. A tabela de movimento ações também é atualizada com o valor do status = false

### Excluir um pedido
  1. Segue a mesma lógica do servioç de atualizar, porém o que muda é o campo situação que fica como **CANCELADO**

## Tecnologias utilizadas
<table>
  <tr>
    <td>Java</td>
    <td>Versão da api do java utilizada</td>
    <td>Spring Boot</td>
    <td>Maven</td>
    <td>PostgreSQL</td>    
    <td>JPA</td>
    <td>Hibernate</td>
    <td>Slf4j</td>
    <td>Mapstruct</td>
    <td>Swagger</td>
    <td>Starter email</td>
  </tr>
  <tr>
    <td>17</td>
    <td>1.8</td>
    <td>3.3.7</td>
    <td>3.8.1</td>
    <td>14.15</td>
    <td>Default</td>
    <td>Default</td>
    <td>Default</td>
    <td>1.5.2.Final</td>
    <td>2.3.0</td>
    <td>Defalut</td>
  </tr>
</table>

## Swagger
* Link: http://localhost:8090/swagger-ui/index.html#/E-mail/sendingEmail

## Como rodar a aplicação:
1. Depois do projeto configurado (fazer clone, maven update, ir até a classe **PedidosApplication.java** e rodar a aplicação
2. Sugiro utilizar o STS para quem puder
3. A partir desse momento o swagger já ficará disponível e também para ser acessado via Postman
4. No Postman, basta abrir uma nova request e acrescentar a o endpoint de acordo como está na controller
5. Exemplo de endpoint que obtém todos os artigos: http://localhost:8090/artigo
6. Também é possível buscar os endpoints no Swagger e rodar no Postman

## Exemplos de requisições
### Requests:

1. **ARTIGO**
***

>**POST**

* `{
	"nome": "Cerveja",
	"status": true,
	"quantidade": 100
}`

* `{
	"nome": "Vinho Tinto",
	"status": true,
	"quantidade": 500
}`

<br>

>**PUT**
* **ID:**
2

* **Body:**
`{
	"id": 2,
	"nome": "Vinho Tinto Porto",
	"status": true,
	"quantidade": 600
}`

<br>

>**DELETE**
Obs:. **Esse serviço atualiza o status para false**

* **ID:**
2

<br>

2. **USUÁRIO**
***

>**POST**

* `{
	"nome": "Paulo",
	"email": "pkatho@gmail.com",
	"status": true
}`
* `{
	"nome": "Fernandes",
	"email": "katho.cafe@hotmail.com",
	"status": true
}`

<br>

>**PUT**
* **ID:**
2

* **Body:**
`{
	"id": 2,
	"nome": "Fernandes de Alcântara",
	"email": "katho.cafe@hotmail.com",
	"status": true
}`

<br>

>**DELETE**
Obs:. **Esse serviço atualiza o status para false**

**ID:**
2

<br>

3. **ORDEM**
***

>**POST**

* `{
	"quantidade": 50,
	"artigo": {
	  "id": 1,
	  "nome": "Cerveja",
	  "status": true,
	  "quantidade": 100
	},
	"usuario": {
	  "id": 1,
	  "nome": "Paulo",
	  "email": "pkatho@gmail.com",
	  "status": true
	}
}`

<br>

OBS:. **Aqui no final ele envia um email para o email que foi cadastrado para o usuário que está sendo passado no body desse POST**


<br>

>**PUT**

* **ID:**
1

* **Body:**
 `{
	"id": 1,
	"dataCriacao": "2025-01-10T17:40:22.837182",
	"quantidade": 50,
	"status": true,
	"transacaoId": "42daa543-ab68-4c98-91bf-8c5270a07c8e",
	"situacaoPedido": "CONCLUÍDO",
	"artigo": {
		"id": 1,
		"nome": "Cerveja",
		"status": true,
		"quantidade": 50
	},
	"usuario": {
		"id": 1,
		"nome": "Paulo",
		"email": "pkatho@gmail.com",
		"status": true
	}
}`

<br>

>**DELETE**
- OBS: **Delete não foi implementado**

<br>

4. **E-mail**
***

>**POST**
* Criei um endpoint para esse serviço de enviar email, pois poderia ser utilizado quando verificasse que o status na base de dados de um email fosse = "ERROR"
* Endpoint: **/email/sending-email**

<br>

Exemplo:

* `{
	"ownerRef": "Katho",
	"emailFrom": "pkatho@gmail.com",
	"emailTo": "pkatho@gmail.com", //SUBSTITUIR PELO SEU EMAIL
	"subject": "Conclusão do seu pedido Simplificado",
	"text": "Olá, somos da Pedidos Simplicicados. Parabéns, seu pedido foi concluído com sucesso!"
}`



