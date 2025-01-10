<h1>Serviço de Pedidos Simplificados<h2>
  
> Status: Developing ⚠️

### É uma aplicação web planejada por mim, onde realizo o CRUD para um serviço de pedido simplificado.

## Além do CRUD, implemento outros recursos como:
* É possível um cliente realizar um pedido
* Esse pedido é processado nos seguintes passos
1. Verifica se tem estoque do artigo necessário para o pedido
2. O status do pedido passa para **PROCESSANDO**
3. É gerado e criado um movimento de ação que é gravado base de dados
4. O Artigo tem a sua quantidade atualizada na base de dados
5. Se tudo correr bem o status do pedido passa para **CONCLUÍDO**
6. É enviado um email para o usuário informando que o pedido foi concluído
7. Um log é gravado com a mensagem "Pedido concoluído."

* É possível pesquisar um pedido por id, status e também buscar todos os pedidos
* Atualizar um pedido
1. O sistema atribui o valor false para o status
2. A situação do pedido fica como **PENDENTE**
3. A quantidade que existe no pedido é retornado para a quantidade do artigo (soma a quantidade de artigo atual com o que havia nesse pedido)
4. A tabela de movimento ações também é atualizada com o valor do status = false

* Excluir um pedido
  1. Segue a mesma lógica do servioç de atualizar, porém o que muda é o campo situação que fica como **CANCELADO**
