package jp.co.msscoop.app.service;

import jp.co.msscoop.app.form.ReserveForm;

public interface ReserveUpdateService {

	ReserveForm input();
	ReserveForm confirm(ReserveForm registerForm);
	String register(ReserveForm registerForm);
}
