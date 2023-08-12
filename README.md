# Voll.med API - Backend
API REST desenvolvida durante meus estudos de Java [Spring Security](https://docs.spring.io/spring-security/reference/index.html) utilizando **Spring 3.1**
<br>
Essa API foi projetada para possibilitar o cadastro, edição e exclusão de médicos e pacientes em uma aplicação fictícia. 
## Requisitos 💻
- Para rodar esse projeto você precisa ter o Java 17 instalado na sua máquina.

## Gerando o JAR 🚀
- Navegue até a pasta raiz do projeto onde fica localizado o arquivo pom.xml
- Abra o console no diretorio e execute a seguinte linha abaixo:
```bash
mvn package
```

## Executando o JAR 📦
- Navegue até a pasta target
- Abra o console no diretorio e execute a seguinte linha abaixo:
```bash
java -jar voll-med-0.0.1-SNAPSHOT
```

# Rotas da Voll.med 📖

> ### Métodos POST 🕊️
#### Realizar Login e gerar Token JWT `.../login`

```json
{
 "login": "string",
 "senha": "string"
}
```

#### Cadastrar um novo médico `.../medicos`

```json
{
  "email": "string",
  "especialidade": "string",
  "telefone": "string",
  "endereco": {
    "logradouro": "string",
    "bairro": "string",
    "cep": "string",
    "cidade": "string",
    "uf": "string",
    "complemento": "string",
    "numero": "string"
  }
}
```

#### Cadastrar um novo paciente `.../pacientes`

```json
{
  "nome": "string",
  "email": "string",
  "cpf": "string",
  "telefone": "string",
  "endereco": {
    "logradouro": "string",
    "bairro": "string",
    "cep": "string",
    "cidade": "string",
    "uf": "string",
    "complemento": "string",
    "numero": "string"
  }
}
```

> ### Métodos GET 📬
#### Listar todos os médicos cadastrados `.../medicos`
#### Listar todos os pacientes cadastrados `.../pacientes`
#### Listar um médico por id `.../medicos/{id}`
#### Listar um paciente por id `.../pacientes/{id}`
<br>

> ### Métodos PUT 📝
#### Editar os dados de um médico `.../medicos`

```json
{
  "id": "string",
  "nome": "string",
  "especialidade": "string",
  "telefone": "string",
  "endereco": {
    "logradouro": "string",
    "bairro": "string",
    "cep": "string",
    "cidade": "string",
    "uf": "string",
    "complemento": "string",
    "numero": "string"
  }
}
```

#### Editar os dados de um paciente `.../pacientes`

```json
{
  "id": "string",
  "nome": "string",
  "email": "string",
  "telefone": "string",
  "endereco": {
    "logradouro": "string",
    "bairro": "string",
    "cep": "string",
    "cidade": "string",
    "uf": "string",
    "complemento": "string",
    "numero": "string"
  }
}
```
<br>

> ### Métodos DELETE 🗑️
#### Deletar um médico por id `.../medicos/{id}`
#### Deletar um paciente por id `.../pacientes/{id}`
#### Desativar um médico por id `.../medicos/desativar/{id}`
