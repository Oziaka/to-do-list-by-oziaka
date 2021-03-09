package pl.task;

public class TaskMapper {
   public static TaskDto toDto(Task task) {
      return TaskDto.builder()
         .id(task.getId())
         .name(task.getName())
         .description(task.getDescription())
         .term(task.getTerm())
         .dateOfExecution(task.getDateOfExecution())
         .isDone(task.getIsDone())
         .isImportant(task.getIsImportant())
         .isUrgent(task.getIsUrgent())
         .build();
   }

   public static Task toEntity(TaskDto taskDto) {
      return Task.builder()
         .id(taskDto.getId())
         .name(taskDto.getName())
         .description(taskDto.getDescription())
         .term(taskDto.getTerm())
         .dateOfExecution(taskDto.getDateOfExecution())
         .isDone(taskDto.getIsDone())
         .isImportant(taskDto.getIsImportant())
         .isUrgent(taskDto.getIsUrgent())
         .build();
   }
}
