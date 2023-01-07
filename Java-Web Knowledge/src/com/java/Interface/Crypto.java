package com.java.Interface;

// 암호화 기능 인터페이스
public interface Crypto {
	// 암호화
	public String encrypt(String str);
	// 복호화
	public String decrypt(String str);
}
