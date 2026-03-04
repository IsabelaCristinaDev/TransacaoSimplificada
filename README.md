Transação Simplificada
API REST de transferências financeiras entre usuários. Desenvolvida com Java e Spring Boot.

Sobre o Projeto
O sistema permite que usuários do tipo Comum realizem transferências de valores entre si ou para Lojistas. Lojistas apenas recebem transferências — não podem enviá-las. Cada transação passa por validações de negócio e por um serviço externo de autorização antes de ser efetivada.

Funcionalidades
Transferência de valores entre usuários
Validação de tipo de usuário (Comum x Lojista)
Validação de saldo antes da transferência
Autorização via serviço externo
Notificação ao recebedor após a transação
Registro de histórico de transações com data e hora
Tratamento global de exceções com respostas HTTP adequadas
Seed automático do banco de dados na inicialização

Regras de Negócio

Lojistas não podem transferir — apenas receber valores
Saldo suficiente — o pagador deve ter saldo igual ou maior ao valor da transferência
Autorização externa — a transação é validada pelo serviço https://util.devi.tools/api/v2/authorize
Notificação — após a transferência, uma notificação é enviada via https://util.devi.tools/api/v1/notify

Tecnologias Utilizadas

Java 17+
Spring Boot
Spring Data JPA / Hibernate
Spring Security (BCryptPasswordEncoder)
OpenFeign — integração com APIs externas
MySQL
Lombok
Maven

Executando
bash# Clone o repositório
git clone https://github.com/seu-usuario/transacao-simplificada.git

 Acesse a pasta do projeto
cd transacao-simplificada

 Execute projeto 
