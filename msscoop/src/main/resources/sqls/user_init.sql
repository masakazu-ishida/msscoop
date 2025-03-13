

delete from public.r_user;
INSERT INTO public.r_user(
	user_id, password, role)
	VALUES ('12345', '$2a$10$oJYpsCmFmDit/zEM2f7R0uEL7DMccpWpEj6eVP5NsWaeqEWoq9RFe', 'admin');
	
INSERT INTO public.r_user(
	user_id, password, role)
	VALUES ('6789', 'pass', 'admin');

INSERT INTO public.r_user(
	user_id, password, role)
	VALUES ('abcd1', 'pass', 'general');