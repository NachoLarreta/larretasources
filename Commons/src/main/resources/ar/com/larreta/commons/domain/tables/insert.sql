
insert into [SCHEMMA].userstate (id, description) values (1, 'Active');
insert into [SCHEMMA].userstate (id, description) values (2, 'Pending');
insert into [SCHEMMA].userstate (id, description) values (3, 'Inactive');

insert into [SCHEMMA].user (id, nick, password, email, idUserState) values (1, 'root', 'OTVid1Z3STY1a2dvZEs5NDlLMzBsdz09', 'leonel@larreta.com.ar', 1);
insert into [SCHEMMA].role (id, description) values (1, 'Admin');
insert into [SCHEMMA].role (id, description) values (2, 'Default');
insert into [SCHEMMA].profile (id, description) values (1, 'Admin');
insert into [SCHEMMA].profile (id, description) values (2, 'Default');
insert into [SCHEMMA].containsRoles (idProfile, idRole) values (1, 1);
insert into [SCHEMMA].containsRoles (idProfile, idRole) values (1, 2);
insert into [SCHEMMA].containsRoles (idProfile, idRole) values (2, 2);
insert into [SCHEMMA].hasProfiles (idUser, idProfile) values (1, 1);




