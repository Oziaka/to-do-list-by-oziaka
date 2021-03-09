package pl.task;

import org.springframework.stereotype.Component;
import pl.exception.ErrorMap;
import pl.validator.Validator;

import java.time.LocalDateTime;

@Component
public class TaskValidator implements Validator<TaskDto> {

   private final String NAME_FIELD_NAME = "name";
   private final String TERM_FIELD_NAME = "term";
   private final String IS_DONE_FIELD_NAME = "isDone";
   private final String IS_IMPORTANT_FIELD_NAME = "isImportant";
   private final String IS_URGENT_FIELD_NAME = "isUrgent";

   private ErrorMap errorMap;

   @Override
   public ErrorMap validate(TaskDto taskDto) {
      errorMap = new ErrorMap();
      validName(taskDto.getName());
      validTerm(taskDto.getTerm());
      validIsImportant(taskDto.getIsImportant());
      validIsUrgent(taskDto.getIsUrgent());
      return errorMap;
   }

   private void validIsUrgent(Boolean isUrgent) {
      if (isUrgent == null)
         errorMap.addError(IS_URGENT_FIELD_NAME, "Task have to set is urgent");
   }

   private void validIsImportant(Boolean isImportant) {
      if (isImportant == null)
         errorMap.addError(IS_IMPORTANT_FIELD_NAME, "Task have to set is important");
   }

   private void validTerm(LocalDateTime term) {
      if (term == null)
         errorMap.addError(TERM_FIELD_NAME, "Task have to set term");
   }

   private void validName(String name) {
      if (name == null)
         errorMap.addError(NAME_FIELD_NAME, "Task have to set name");
   }
}
