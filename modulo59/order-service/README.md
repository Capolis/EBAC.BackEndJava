# Order Service

## Descrição
O Serviço de Pedidos atua como o orquestrador central da arquitetura de microsserviços de e-commerce. Ele recebe a solicitação de checkout e coordena chamadas HTTP síncronas para os outros domínios a fim de concluir a transação.

## Arquitetura e Resiliência
Este serviço implementa o padrão **API Composition** (Composição de API). Ele não mantém chaves estrangeiras físicas para entidades externas, armazenando apenas referências lógicas (`customerId`, `productId`).

Para evitar falhas em cascata, este serviço é protegido pelo **Resilience4j**. Se algum serviço dependente (Cliente, Estoque ou Pagamento) ficar indisponível, um *Circuit Breaker* interrompe as requisições e aciona um método de *fallback*, registrando o pedido como `PENDING_MANUAL_REVIEW` para processamento assíncrono.

## Conexões
Este serviço comunica-se externamente utilizando o **Spring Cloud OpenFeign**:
* `GET` -> Serviço de Clientes (Porta `8081`)
* `POST` -> Serviço de Estoque (Porta `8082`)
* `POST` -> Serviço de Pagamento (Porta `8083`)

## Documentação da API
Acesse a documentação interativa da API via Swagger UI:
`http://localhost:8080/swagger-ui/index.html`