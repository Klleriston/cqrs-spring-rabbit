# CQRS Course - Arquitetura CQRS

## O que é CQRS?

CQRS (Command Query Responsibility Segregation) é um padrão arquitetural que separa as operações de leitura (queries) das operações de escrita (commands) em um sistema. Essa separação permite otimizar cada lado de forma independente, melhorando performance, escalabilidade e manutenibilidade.

### Conceitos Principais

**Commands (Comandos)**
- Representam intenções de mudança no sistema
- Modificam o estado da aplicação
- Exemplos: CriarUsuario, AtualizarPedido, DeletarProduto
- Retornam apenas confirmação de sucesso/falha

**Queries (Consultas)**
- Responsáveis por recuperar dados
- Não modificam o estado do sistema
- Otimizadas para leitura
- Podem ter modelos de dados diferentes do lado de escrita

### Vantagens do CQRS

- **Escalabilidade**: Leitura e escrita podem escalar independentemente
- **Performance**: Cada lado pode ser otimizado para seu propósito específico
- **Flexibilidade**: Múltiplos modelos de leitura para diferentes casos de uso
- **Separação de Responsabilidades**: Código mais organizado e manutenível
- **Auditoria**: Facilita o rastreamento de mudanças através de eventos

### Desvantagens e Considerações

- Maior complexidade inicial
- Eventual consistência entre leitura e escrita
- Duplicação de dados
- Curva de aprendizado mais acentuada