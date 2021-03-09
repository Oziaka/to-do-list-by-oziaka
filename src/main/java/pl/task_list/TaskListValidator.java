package pl.task_list;

import org.springframework.stereotype.Component;
import pl.exception.ErrorMap;
import pl.validator.Validator;

@Component
public class TaskListValidator implements Validator<TaskList> {

   private final String NAME_FIELD_NAME = "name";
   private ErrorMap errorMap;

   @Override
   public ErrorMap validate(TaskList object) {
      validateName(object.getName());
      return errorMap;
   }

   private void validateName(String name) {
      if (name == null)
         errorMap.addError(NAME_FIELD_NAME, "Task list should have name");
   }
}
