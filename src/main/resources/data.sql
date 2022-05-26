insert into student(id, name, email, phone)
values (1, 'Филипп', 'phillippko@gmail.com', '89211011010');

insert into lector(id, name)
values (1, 'Хахина Анна Михайловна');

insert into lection(id, name, lector_id)
values (1, 'Теория вероятностей', 1);

insert into homework(id, mark, student_id, lection_id) values(1,5, 1, 1);

insert into lection_student_list (lection_id, student_list_id) values (1,1);
insert into student_courses_list (student_id, courses_list_id) values (1,1);
insert into student_homework_list (student_id, homework_list_id) values (1,1);