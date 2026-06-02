-- Dados controlados e previsíveis para os testes de integração.
-- IDs seguem a ordem de inserção (H2 IDENTITY começa em 1).
--
-- Album id 1: Thriller  → 2 músicas (ids 1 e 2)
-- Album id 2: Back in Black → 1 música (id 3)  ← usado no teste de DELETE
-- Album id 3: 21           → 1 música (id 4)
--
-- Média das notas: (9.7 + 9.3 + 8.9 + 9.1) / 4 = 9.25

INSERT INTO album (nome, data_lancamento) VALUES ('Thriller', '1982-11-30');
INSERT INTO album (nome, data_lancamento) VALUES ('Back in Black', '1980-07-25');
INSERT INTO album (nome, data_lancamento) VALUES ('21', '2011-01-24');

INSERT INTO musica (nome, album_id, nota) VALUES ('Billie Jean', 1, 9.7);
INSERT INTO musica (nome, album_id, nota) VALUES ('Beat It', 1, 9.3);
INSERT INTO musica (nome, album_id, nota) VALUES ('Back in Black', 2, 8.9);
INSERT INTO musica (nome, album_id, nota) VALUES ('Rolling in the Deep', 3, 9.1);
