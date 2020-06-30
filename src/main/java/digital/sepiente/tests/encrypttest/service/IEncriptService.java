package digital.sepiente.tests.encrypttest.service;

import digital.sepiente.tests.encrypttest.exception.DemoException;

public interface IEncriptService {
	public String encript(String str) throws DemoException;

	public String decript(String encrypted) throws DemoException;
}
