
/*シーケンスの削除と作成*/
DROP SEQUENCE IF EXISTS public.seq_reserve_id;
CREATE SEQUENCE IF NOT EXISTS public.seq_reserve_id
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.seq_reserve_id
    OWNER TO postgres;


/*レコードを全件削除*/
delete from public.e_reserve;
delete from public.r_reservavle_room_info;
delete from public.r_room;
delete from public.r_user;

/*ユーザ情報の登録　パスワードは全て共通の【pass01】*/
insert into public.r_user(user_id,password,role,full_name,email) values 
 (N'user01',N'$2a$10$Ls/k7.BCg2Wz/5HzXZZEke5/Gv5nG8dfWWMoL.Tf9xv0P5D2M/oRC',N'ADMIN',N'鳥取一郎',N'mishida@support-s-frontier.com')
,(N'user02',N'$2a$10$Ls/k7.BCg2Wz/5HzXZZEke5/Gv5nG8dfWWMoL.Tf9xv0P5D2M/oRC',N'ADMIN',N'鳥取四郎',N'toriyonn@gmail.com')
,(N'user03',N'$2a$10$Ls/k7.BCg2Wz/5HzXZZEke5/Gv5nG8dfWWMoL.Tf9xv0P5D2M/oRC',N'USER',N'鳥取三郎',N'torisan@gmail.com')
;

/*お部屋情報の登録*/
insert into public.r_room(room_id,room_name,price,room_image,smoking,indoor_bathroom) values 
 (N'202501050001',N'雁の間',100000,N'/images/202501050001.png',false,true)
,(N'202501050002',N'鶴の間',65000,N'/images/202501050002.png',false,true)
,(N'202501050003',N'雉の間',35000,N'/images/202501050003.png',false,true)
;

/*
予約可能部屋情報の登録
各部屋ともに、2025-01-06～2025-01-10まで予約可能で登録済み*/
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

/*予約情報の登録*/
insert into public.e_reserve(reserve_id,room_id,checkin,checkout,stay_number_of_people,meal,amount,user_id,cancel) values 
 (N'202506290002',N'202501050003',DATE '2025-01-06',DATE '2025-01-07',2,true,70000,N'user01',false)
,(N'202506290003',N'202501050001',DATE '2025-01-06',DATE '2025-01-07',2,true,200000,N'user01',true)
,(N'202507120004',N'202501050002',DATE '2025-01-06',DATE '2025-01-07',2,true,130000,N'user01',true)
;

