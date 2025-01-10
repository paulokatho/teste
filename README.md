![realist](https://github.com/user-attachments/assets/7a913cf8-bc3d-4555-bde1-bcef6a0ee7d3)

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
4. No Postman, basta abrir uma nova request e acrescentar a 
