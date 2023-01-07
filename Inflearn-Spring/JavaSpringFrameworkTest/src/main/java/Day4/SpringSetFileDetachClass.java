package Day4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringSetFileDetachClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 분리한 xml파일을 get 하려고 하면?
		
		String[] appCtxs = {"classpath:appCtx1.xml", "classpath:appCtx2.xml", "classpath:appCtx3.xml"};
		
		// 위에 xml 파일들을 배열로 가져온 값을 아래에 선언한 값에 넣는다
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(appCtxs);
	
		// 사용할 때는 똑같이 ctx.getBean()으로 사용하면 된다
		
		
		// Import한 xml파일을 가져올 경우, 흔하게 사용하는 편은 아님
		GenericXmlApplicationContext ctxImport = new GenericXmlApplicationContext("classpath:appCtxImport.xml");
	}

}
