Customer

Train
->id,name,src,dest,listOfStops
->Coach: id, type, cost
->Berth: id, type , count
coach_berth: coach_id, berth_id
train_coach: train_id, coach_id, coach_count


reservation
->PNR, train_id, amount, status
->booking_details: coach_id, berth_id, status, (traveller_id, PNR)


berth_availablity
->train_id, coach_id, count





insert into train values (1,'hyd','hyd express','blr',5);
insert into berth_availability values(1,20,1,'2020-06-15','LOWER','FIRST');



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



insert into coach values(1,'FIRST',3,1500,1,1);
insert into coach values(2,'SECOND',3,1200,2,2);
insert into coach values(3,'FIRST',3,1880,2,1);
insert into coach values(4,'SECOND',3,1700,1,2);
insert into coach values(5,'GENERAL',3,1500,1,4);


insert into berth_availability values(15,18,2,'2020-06-15','UPPER','FIRST');
insert into berth_availability values(16,23,2,'2020-06-15','MIDDLE','FIRST');
insert into berth_availability values(17,68,2,'2020-06-15','UPPER','GENERAL');
insert into berth_availability values(18,109,2,'2020-06-15','MIDDLE','GENERAL');
insert into berth_availability values(19,109,2,'2020-06-15','MIDDLE','SECOND');
insert into berth_availability values(20,8,2,'2020-06-15','LOWER','FIRST');


insert into berth values(1,'LOWER',50);
insert into berth values(2,'UPPER',50);
insert into berth values(3,'MIDDLE',50);
insert into berth values(4,'SIDE_UPPER',50);
insert into berth values(5,'SIDE_LOWER',50);


insert into coach_berth values(1,1,1);
insert into coach_berth values(1,2,1);
insert into coach_berth values(1,1,2);
insert into coach_berth values(1,2,2);
insert into coach_berth values(1,1,3);
insert into coach_berth values(1,2,3);
insert into coach_berth values(1,1,4);
insert into coach_berth values(1,2,4);
insert into coach_berth values(1,1,5);
insert into coach_berth values(1,2,5);

insert into coach_berth values(2,1,1);
insert into coach_berth values(2,2,1);
insert into coach_berth values(2,1,2);
insert into coach_berth values(2,2,2);
insert into coach_berth values(2,1,3);
insert into coach_berth values(2,2,3);
insert into coach_berth values(2,1,4);
insert into coach_berth values(2,2,4);
insert into coach_berth values(2,1,5);
insert into coach_berth values(2,2,5);