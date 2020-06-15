Customer
Passenger

Train
->id,name,src,dest,listOfStops
->Coach: train_id, coach_id, type, coach_count, cost
->Berth: id, type, berth_count
coach_berth: coach_id, berth_id


reservation
->PNR, train_id, amount, status
->booking_details: coach_id, berth_id, status, (traveller_id, PNR)


berth_availablity
->train_id, coach_id, count





insert into train values (1,'hyd','hyd express','blr',5);
insert into coach values(1,'FIRST');
insert into berth_availability values(1,20,1,'2020-06-15','LOWER','FIRST');


insert into coach values(2,'SECOND');
insert into coach values(3,'THIRD');
Insert into coach values(4,'GENERAL');

insert into berth_availability values(2,20,1,'2020-06-15','UPPER','FIRST');
insert into berth_availability values(3,20,1,'2020-06-15','MIDDLE','FIRST');
insert into berth_availability values(4,30,1,'2020-06-15','SIDE_LOWER','FIRST');
insert into berth_availability values(5,30,1,'2020-06-15','SIDE_UPPER','FIRST');
insert into berth_availability values(6,20,1,'2020-06-15','UPPER','SECOND');
insert into berth_availability values(7,20,1,'2020-06-15','MIDDLE','SECOND');
insert into berth_availability values(8,30,1,'2020-06-15','SIDE_LOWER','SECOND');
insert into berth_availability values(9,30,1,'2020-06-15','SIDE_UPPER','SECOND');
insert into berth_availability values(10,20,1,'2020-06-15','LOWER','SECOND');


insert into train values (2,'hyd','hyd express','delhi',15);
insert into berth_availability values(11,20,1,'2020-06-15','UPPER','FIRST');
insert into berth_availability values(12,20,1,'2020-06-15','MIDDLE','FIRST');
insert into berth_availability values(13,20,1,'2020-06-15','UPPER','GENERAL');
insert into berth_availability values(14,20,1,'2020-06-15','MIDDLE','GENERAL');



insert into train_coach values(1,3,1500,'FIRST',1);
insert into train_coach values(1,3,1200,'SECOND',2);
insert into train_coach values(2,3,1880,'FIRST',1);
insert into train_coach values(2,3,1700,'SECOND',2);
insert into train_coach values(2,3,1500,'GENERAL',3);


insert into berth_availability values(15,18,2,'2020-06-15','UPPER','FIRST');
insert into berth_availability values(16,23,2,'2020-06-15','MIDDLE','FIRST');
insert into berth_availability values(17,68,2,'2020-06-15','UPPER','GENERAL');
insert into berth_availability values(18,109,2,'2020-06-15','MIDDLE','GENERAL');
insert into berth_availability values(19,109,2,'2020-06-15','MIDDLE','SECOND');
insert into berth_availability values(20,8,2,'2020-06-15','LOWER','FIRST');
