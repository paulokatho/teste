CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE ordem (
	id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
	data_criacao TIMESTAMP NOT NULL,
	quantidade INTEGER NOT NULL,
	status BOOLEAN NOT NULL,
	artigo_id UUID,
	usuario_id UUID,
	FOREIGN KEY (artigo_id) REFERENCES artigo(id),
	FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);
