# Use a imagem oficial do PostgreSQL como base
FROM postgres:latest

# Defina variáveis de ambiente para o nome do banco de dados e esquema
ENV POSTGRES_DB dados
ENV POSTGRES_SCHEMA bd_schema

# Copie o script SQL para o diretório de inicialização do PostgreSQL
COPY init.sql /docker-entrypoint-initdb.d/
