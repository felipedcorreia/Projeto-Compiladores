# Projeto Compiladores

##  Getting Started

#### Cópia do projeto
```bash
git clone git@github.com:felipedcorreia/Projeto-Compiladores.git  
```

#### Download antlr4
```bash
curl -O https://www.antlr.org/download/antlr-4.7.1-complete.jar
```

#### A cada modificação de IsiLang.g4
```bash
java -cp .:antlr-4.7.1-complete.jar org.antlr.v4.Tool IsiLang.g4 -package br.com.professorisidro.isilanguage.parser -o ./src/br/com/professorisidro/isilanguage/parser/
```