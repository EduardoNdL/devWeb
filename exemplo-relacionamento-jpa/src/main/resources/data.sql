INSERT INTO album (nome, data_lancamento)
VALUES
    ('A Night at the Opera', '1975-11-21'),
    ('Thriller', '1982-11-30'),
    ('Back in Black', '1980-07-25'),
    ('21', '2011-01-24'),
    ('Random Access Memories', '2013-05-17');

INSERT INTO musica (nome, album_id, nota)
VALUES
    -- Album 1: Queen
    ('Bohemian Rhapsody', 1, 9.8),
    ('Love of My Life', 1, 9.2),

    -- Album 2: Michael Jackson
    ('Billie Jean', 2, 9.7),
    ('Beat It', 2, 9.3),

    -- Album 3: AC/DC
    ('Back in Black', 3, 8.9),
    ('Hells Bells', 3, 8.7),

    -- Album 4: Adele
    ('Rolling in the Deep', 4, 9.1),
    ('Someone Like You', 4, 9.4),

    -- Album 5: Daft Punk
    ('Get Lucky', 5, 8.8),
    ('Instant Crush', 5, 8.5);