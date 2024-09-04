delete from users where 1 = 1;
delete from cats_friends where 1 = 1;
delete from owners_cats where 1 = 1;
delete from cats where 1 = 1;
delete from owners where 1 = 1;
INSERT INTO owners(id, name, birth_date)
values (1, 'Vasya', '2022-02-02'),
       (2, 'Vasya', '2022-02-02');
INSERT INTO users(id, name, password, role, owner)
values (1, 'bob', '1234', 0, 1),
       (2, 'bobby', '1234', 1, 2);
INSERT INTO cats(id, name, birth_date, breed, color, owner)
values (1, 'Kit', '2022-02-02', 2, 0, 1),
(2, 'Kit2', '2022-02-02', 2, 3, 1),
(3, 'Kit3', '2022-02-02', 3, 2, 1),
(4, 'Kit4', '2022-02-02', 2, 0, 2);