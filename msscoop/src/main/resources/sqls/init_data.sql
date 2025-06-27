delete from public.e_reserve;
delete from public.r_reservavle_room_info;
delete from public.r_room;
delete from public.r_user;
delete from public.r_meal_type;
delete from public.r_customer;



insert into public.r_user(user_id,password,role,full_name,email) values 
 (N'12345',N'$2a$10$oJYpsCmFmDit/zEM2f7R0uEL7DMccpWpEj6eVP5NsWaeqEWoq9RFe',N'ROLE_ADMIN',N'鳥取一郎',N'mishida@support-s-frontier.com')
,(N'123456EX',N'$2a$10$oJYpsCmFmDit/zEM2f7R0uEL7DMccpWpEj6eVP5NsWaeqEWoq9RFe',N'ROLE_ADMIN',N'鳥取四郎',N'toriyonn@gmail.com')
,(N'6789',N'6789',N'ROLE_ADMIN',N'鳥取三郎',N'torisan@gmail.com')
;


insert into public.r_room(room_id,room_name,price,room_image,smoking,indoor_bathroom) values 
 (N'202501050001',N'雁の間',100000,N'/images/202501050001.png',false,true)
,(N'202501050002',N'鶴の間',65000,N'/images/202501050002.png',false,true)
,(N'202501050003',N'雉の間',35000,N'/images/202501050003.png',false,true)
;

insert into public.r_meal_type(meal_type_id,meal_type_name) values 
 (N'202501050001',N'朝夕')
,(N'202501050002',N'朝のみ')
,(N'202501050003',N'夕のみ')
,(N'202501050004',N'朝昼夕')
,(N'202501050005',N'なし')
;


insert into public.r_customer(customer_id,customer_name,mail,telnumber,address) values 
 (N'202501050001',N'中野太郎',N'nakano@gmail.com',N'09011112233',N'東京都中野区中野４丁目１１?１９')
,(N'202501050002',N'福島太郎',N'fukushima@gmail.com',N'09011112244',N'福島県福島市杉妻町２?１６')
,(N'202501050003',N'秋田花子',N'akita@gmail.com',N'09011112255',N'秋田県秋田市山王４丁目１ー１')
,(N'202501050004',N'鳥取花子',N'tottori@gmail.com',N'09011112266',N'鳥取県鳥取市東町１丁目２２０')
,(N'202501050005',N'岡山太郎',N'okayama@gmail.com',N'09011112277',N'岡山県岡山市北区内山下２丁目４ー６')
,(N'202501050006',N'福岡太郎',N'fukuoka@gmail.com',N'09011112288',N'福岡県福岡市博多区東公園７７')
;

insert into public.r_reservavle_room_info(room_id,businessday) values 
 (N'202501050001',DATE '2025-01-06')
,(N'202501050001',DATE '2025-01-07')
,(N'202501050001',DATE '2025-01-08')
,(N'202501050001',DATE '2025-01-09')
,(N'202501050001',DATE '2025-01-10')
,(N'202501050002',DATE '2025-01-06')
,(N'202501050002',DATE '2025-01-07')
,(N'202501050002',DATE '2025-01-08')
,(N'202501050002',DATE '2025-01-09')
,(N'202501050002',DATE '2025-01-10')
,(N'202501050003',DATE '2025-01-06')
,(N'202501050003',DATE '2025-01-07')
,(N'202501050003',DATE '2025-01-08')
,(N'202501050003',DATE '2025-01-09')
,(N'202501050003',DATE '2025-01-10')
;



insert into public.e_reserve(reserve_id,room_id,checkin,checkout,stay_number_of_people,meal,amount,user_id,cancel) values 
 (N'202501060001',N'202501050002',DATE '2025-01-06',DATE '2025-01-07',2,true,130000,N'12345',N'0')
,(N'202501060002',N'202501050003',DATE '2025-01-06',DATE '2025-01-07',2,true,70000,N'12345',N'0')
,(N'202501060003',N'202501050001',DATE '2025-01-06',DATE '2025-01-07',2,true,200000,N'12345',N'0')
,(N'202501070001',N'202501050002',DATE '2025-01-07',DATE '2025-01-08',2,true,130000,N'12345',N'0')
,(N'202501070002',N'202501050003',DATE '2025-01-07',DATE '2025-01-08',2,true,70000,N'12345',N'0')
,(N'202501080001',N'202501050001',DATE '2025-01-08',DATE '2025-01-09',2,true,200000,N'12345',N'0')
,(N'202501090001',N'202501050001',DATE '2025-01-09',DATE '2025-01-10',2,true,200000,N'12345',N'0')
;