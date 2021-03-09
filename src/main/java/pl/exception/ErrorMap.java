package pl.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ErrorMap extends HashMap<String, List<String>> {
   public void addError(String field, String message) {
      super.computeIfAbsent(field, l -> new ArrayList<>(Collections.singletonList(message)));
   }

   public boolean hasErrors() {
      return !super.isEmpty();
   }
}
