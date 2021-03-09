package pl;

import java.util.ArrayList;
import java.util.HashMap;

public class ErrorMap extends HashMap<String, ArrayList<String>> {
   public boolean hasError() {
      return super.isEmpty();
   }

   public void addError(String fieldName, String message) {
      super.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(message);
   }
}
