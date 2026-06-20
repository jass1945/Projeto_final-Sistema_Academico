# Sistema Acadêmico

API REST para gerenciamento de alunos, professores e turmas.

Grupo: Juan Sartori Alticimo

## Tecnologias

- Java 26
- Spring Boot 4.1.0
- Spring Data JPA + Validation
- Banco H2 (em memória)
- Maven

## Como rodar

Certifique-se de ter o Java instalado, depois execute o projeto pelo IntelliJ. 

Ou execute na raiz do projeto pelo CMD (Prompt de Comando):

```bash
mvnw.cmd spring-boot:run
```

A API sobe em `http://localhost:8080`.

Para visualizar o banco de dados, acesse `http://localhost:8080/h2-console` com as configurações:
- JDBC URL: `jdbc:h2:mem:escoladb`
- Usuário: `sa`
- Senha: (deixe em branco)

---

## Endpoints

### Alunos

```
POST   /api/alunos
GET    /api/alunos
GET    /api/alunos?nome=João
GET    /api/alunos/{id}
PUT    /api/alunos/{id}
DELETE /api/alunos/{id}
```

Body (POST e PUT):
```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "matricula": "MAT001"
}
```

### Professores

```
POST   /api/professores
GET    /api/professores
GET    /api/professores?nome=Maria
GET    /api/professores/{id}
PUT    /api/professores/{id}
DELETE /api/professores/{id}
```

Body (POST e PUT):
```json
{
  "nome": "Maria Souza",
  "email": "maria@email.com",
  "especialidade": "Matemática"
}
```

### Turmas

```
POST   /api/turmas
GET    /api/turmas
GET    /api/turmas?disciplina=Mat
GET    /api/turmas?notaMinima=7.0
GET    /api/turmas?notaMaxima=5.0
GET    /api/turmas?frequenciaMinima=75
GET    /api/turmas/{id}
PUT    /api/turmas/{id}
DELETE /api/turmas/{id}
```

Body (POST) — use o id de um professor e aluno já cadastrados:
```json
{
  "nomeDisciplina": "Matemática",
  "nota": 8.5,
  "frequencia": 90,
  "professor": { "id": 1 },
  "aluno": { "id": 1 }
}
```

---

## Validações

Campos obrigatórios em todas as entidades — a API retorna 400 com a lista de erros caso algum esteja ausente ou inválido.

- `nome`, `email`, `matricula`, `especialidade`, `nomeDisciplina` não podem ser nulos ou vazios
- `email` deve ter formato válido
- `nota` deve estar entre 0.0 e 10.0
- `frequencia` deve estar entre 0 e 100
- `professor` e `aluno` devem existir no banco

## Paginação

Todos os GETs de listagem aceitam os parâmetros `page`, `size` e `sort`:

```
GET /api/alunos?page=0&size=10&sort=nome,asc
```
