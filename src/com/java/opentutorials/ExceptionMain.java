package com.java.opentutorials;

import java.io.FileWriter;

import javax.xml.ws.handler.MessageContext.Scope;

public class ExceptionMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 생활 코딩의 JAVA 예외
		
		int[] scores = {10,20,30,};
		// System.out.println(2/0); // Zero 값의 오류가 발생 -> try catch문 사용!!
		
		try {
			System.out.println(2/0);
			System.out.println(scores[3]);
		} catch (Exception e) { // 예외는 Exception가 부모 Exception
			// TODO: handle exception
			System.out.println("에러가 났어요. " + e.getMessage());
			e.printStackTrace();
		}
		
		// Checked vs Unchecked
		
		// 1. CheckedException => 반드시 try catch문 혹은 throws로 반드시 예외를 조치해야 한다!
		FileWriter f = null;
		try { 
			f = new FileWriter("Checked.txt");
			f.write("생활코딩");
			// 여기서 예외가 발생하기 때문에 close는 finally로 처리해야
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (f != null) {
				try {
					f.close(); // try catch문을 사용하지 않으면 에러가 난다
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		
		// 2. UncheckedException => ArithmeticException과 같은 Exception은 예외 조치를 하지 않아도 실행이 된다.
		
		// 3. try with resource statement
		try(FileWriter f2 = new FileWriter("TryWithResource.txt")) { // ()안에 close할 것을 넣는다		
			f2.write("생활코딩");
			f2.close(); // 위 ()안에 있는 것과 동일한 내용. 자동으로 close를 내부적으로 실행
			// 위의 finally를 사용한 코드와 같은 기능을 한다. 코드는 짧지만 기능은 같음
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		// 4. throw 로 예외를 던지다 -> 직접 예외를 발생시킬 수 있다
		throw new RuntimeException("무언가 문제가 있습니다.");
		
		// 5. public static void main(String[] args) throws IOException {} 을 하게 되면 코드에서 예외를 처리하는 것이 아니라 코드를 사용하는 쪽(main)에서 예외를 처리하게 된다.
	}

}
