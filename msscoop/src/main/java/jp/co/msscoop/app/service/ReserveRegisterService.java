package jp.co.msscoop.app.service;

import jp.co.msscoop.app.form.ReserveForm;

public interface ReserveRegisterService {
	ReserveForm input(ReserveForm registerForm);
	ReserveForm confirm(ReserveForm registerForm);
	String register(ReserveForm registerForm);
}
