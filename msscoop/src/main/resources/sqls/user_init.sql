

delete from public.r_user;
INSERT INTO public.r_user(
	user_id, password, role)
	VALUES ('12345', 'pass', 'admin');
	
INSERT INTO public.r_user(
	user_id, password, role)
	VALUES ('6789', 'pass', 'admin');

INSERT INTO public.r_user(
	user_id, password, role)
	VALUES ('abcd1', 'pass', 'general');