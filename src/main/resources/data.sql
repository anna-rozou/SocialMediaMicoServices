--USERS
insert into user_table(id, name, birth_date)
values('e4018005-7146-4ad1-83e4-17a98105c3fd', 'Anna Rozou', current_date);

insert into user_table(id, name, birth_date)
values('f1234a88-718a-4660-9723-b78d6b8096d6', 'Jim Rozos', current_date);

insert into user_table(id, name, birth_date)
values('7994e0be-b4ed-4fa4-b150-6d01cd16ca29', 'Linos Rozos', current_date);

insert into user_table(id, name, birth_date)
values('eb27d57e-5273-44c9-8dd1-f9caedd046dc', 'Panos Rozos', current_date);

insert into user_table(id, name, birth_date)
values('14330fba-1a87-46f9-9225-a181e22d1f5f', 'Olga Chourmouz', current_date);

--POSTS
insert into post_table(id, description, user_id)
values(random_uuid(), 'PostAnna1', 'e4018005-7146-4ad1-83e4-17a98105c3fd');

insert into post_table(id, description, user_id)
values(random_uuid(), 'PostAnna2', 'e4018005-7146-4ad1-83e4-17a98105c3fd');

insert into post_table(id, description, user_id)
values(random_uuid(), 'PostAnna3', 'e4018005-7146-4ad1-83e4-17a98105c3fd');

insert into post_table(id, description, user_id)
values(random_uuid(), 'PostJim1', 'f1234a88-718a-4660-9723-b78d6b8096d6');

insert into post_table(id, description, user_id)
values(random_uuid(), 'PostJim2', 'f1234a88-718a-4660-9723-b78d6b8096d6');

insert into post_table(id, description, user_id)
values(random_uuid(), 'PostJim3', 'f1234a88-718a-4660-9723-b78d6b8096d6');
