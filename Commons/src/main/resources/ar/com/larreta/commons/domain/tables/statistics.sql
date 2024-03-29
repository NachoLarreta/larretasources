CREATE TABLE [SCHEMMA].visitorstatistics (
	id BIGINT NOT NULL,
	deleted DATETIME,
	url VARCHAR(1000),
	ip VARCHAR(255),
	browser VARCHAR(255),
	operatingSystem VARCHAR(255),
	device VARCHAR(255),
	date DATETIME,
	idUser BIGINT,
	PRIMARY KEY (ID)
);

CREATE TABLE [SCHEMMA].executionstatistics (
	id BIGINT NOT NULL,
	deleted DATETIME,
	mark VARCHAR(1000),
	start DATETIME,
	end DATETIME,
	milliseconds BIGINT,
	idUser BIGINT,
	PRIMARY KEY (ID)
);

ALTER TABLE [SCHEMMA].visitorstatistics
ADD CONSTRAINT fk_user_in_visitorstatistics
FOREIGN KEY (idUser)
REFERENCES [SCHEMMA].user(id);

ALTER TABLE [SCHEMMA].executionstatistics
ADD CONSTRAINT fk_user_in_executionstatistics
FOREIGN KEY (idUser)
REFERENCES [SCHEMMA].user(id);