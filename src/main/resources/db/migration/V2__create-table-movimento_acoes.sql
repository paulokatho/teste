CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE movimento_acoes (
	id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
	data_criacao TIMESTAMP NOT NULL,
	quantidade INTEGER NOT NULL,
	artigo_id UUID,
	FOREIGN KEY (artigo_id) REFERENCES artigo(id)
);
