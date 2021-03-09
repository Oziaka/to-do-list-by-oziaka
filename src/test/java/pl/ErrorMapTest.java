package pl;

import org.junit.jupiter.api.Test;

public class ErrorMapTest {

   @Test
   void test(){
      ErrorMap errorMap = new ErrorMap();
      System.out.println(errorMap.isEmpty());
      errorMap.addError("Name","too short");
      System.out.println(errorMap.isEmpty());
      errorMap.addError("Name","too short");
      errorMap.addError("dsa","too short");
      errorMap.addError("dsa","too short");
      errorMap.addError("dsa","too short");
      System.out.println(errorMap);
      System.out.println(errorMap.isEmpty());

   }
}
