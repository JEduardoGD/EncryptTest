package com.example.demo.service;

import com.example.demo.exception.DemoException;

public interface IEncriptService {
	public String encript(String str) throws DemoException;

	public String decript(String encrypted) throws DemoException;
}
