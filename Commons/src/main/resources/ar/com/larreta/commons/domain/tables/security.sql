CREATE TABLE [SCHEMMA].securityMatcher (
	id BIGINT NOT NULL,
	deleted DATETIME,
	matcherType VARCHAR(255) NOT NULL,
	pattern VARCHAR(255) NOT NULL,
	idSecurity BIGINT NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE [SCHEMMA].securityMatcherRoles (
	idRole BIGINT NOT NULL,
	idSecurityMatcher BIGINT NOT NULL,
	PRIMARY KEY (idRole, idSecurityMatcher)
);

CREATE TABLE [SCHEMMA].security (
	id BIGINT NOT NULL,
	deleted DATETIME,
	loginPage VARCHAR(255) NOT NULL,
	loginProcessingUrl VARCHAR(255) NOT NULL,
	defaultSuccessUrl VARCHAR(255) NOT NULL,
	failureUrl VARCHAR(255) NOT NULL,
	usernameParameter VARCHAR(255) NOT NULL,
	passwordParameter VARCHAR(255) NOT NULL,
	logoutUrl VARCHAR(255) NOT NULL,
	logoutSuccessUrl VARCHAR(255) NOT NULL,
	deleteCookies VARCHAR(255) NOT NULL,
	PRIMARY KEY (ID)
);

ALTER TABLE [SCHEMMA].securityMatcher
ADD CONSTRAINT fk_security_in_securityMatcher
FOREIGN KEY (idSecurity)
REFERENCES [SCHEMMA].security(id);

ALTER TABLE [SCHEMMA].securityMatcherRoles
ADD CONSTRAINT fk_rol_in_securityMatcherRoles
FOREIGN KEY (idRole)
REFERENCES [SCHEMMA].role(id);

ALTER TABLE [SCHEMMA].securityMatcherRoles
ADD CONSTRAINT fk_securityMatcher_in_securityMatcherRoles
FOREIGN KEY (idSecurityMatcher)
REFERENCES [SCHEMMA].securityMatcher(id);
