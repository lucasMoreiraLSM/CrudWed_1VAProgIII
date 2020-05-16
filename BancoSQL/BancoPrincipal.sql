/*Tabela Principal PESSOA*/

CREATE TABLE public.pessoa
(
  id_pessoa  SERIAL PRIMARY KEY NOT NULL,
  nome character varying(50) NOT NULL,
  sexo character(1),
  data_nascimento character varying(12),
  cpf character varying(15),
  senha character varying(50),
  end_rua character varying(60),
  end_bairro character varying(40),
  end_numero character varying(40),
  end_complemento character varying(60)
  
  
  
);

/*Tabela Axuliar Cidade*/

CREATE TABLE public.cidade
(
   id_cidade SERIAL PRIMARY  KEY NOT NULL,
   nome character varying(50),
   estado character varying(50),
   id_pessoa integer,
   CONSTRAINT fk_CidPessoa FOREIGN KEY (id_pessoa) REFERENCES pessoa (id_pessoa)
);