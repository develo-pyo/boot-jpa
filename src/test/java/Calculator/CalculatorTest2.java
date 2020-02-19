package Calculator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest2 {

   //지역변수를 필드로 빼서 사용할 수 있음
   //아래와 같이 필드 초기화를 전역에서 하는 경우 테스트간 독립성이 보장되지 않음
   //@Before 사용시 add() , devide() 테스트 메소드 실행될 때마다 setup() 메소드가 실행되어, 인스턴스 초기화가 매번 실행됨 : 테스트간 독립성 보장
// private Calculator cal = new Calculator();
   private Calculator cal;
   
   @Before
   public void setup() {
      cal = new Calculator();
      System.out.println("setup !!!");
   }
   
   //버전이 달라 assertEquals deprecated 된게 있음
   //특정한 케이스만 테스트 하고 싶은 경우 해당 메소드위에 커서를 두고 junit run 하면 해당 케이스만 테스트함.
   @Test
   public void add() {
      int r = cal.add(2, 3);
      assertEquals("sc", 5, r);
      System.out.println("add !!!");
   }

   // 깨알팁 : 복사단축키
   // 1. 복사할 블락 지정
   // 2. ctrl + alt + 아래화살표
   @Test
   public void devide() {
      int r = cal.devide(3, 3);
      assertEquals("success", 2, r);
      System.out.println("devide !!!");
   }

   //@After 역시 테스트 메소드 실행될 때마다 호출된다
   @After
   public void teardown() {
      System.out.println("teardown !!!");
   }
}
