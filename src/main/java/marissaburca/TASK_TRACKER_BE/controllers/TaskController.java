package marissaburca.TASK_TRACKER_BE.controllers;

import marissaburca.TASK_TRACKER_BE.entities.Task;
import marissaburca.TASK_TRACKER_BE.entities.User;
import marissaburca.TASK_TRACKER_BE.exceptions.BadRequest;
import marissaburca.TASK_TRACKER_BE.payloads.task.TaskDTO;
import marissaburca.TASK_TRACKER_BE.payloads.task.TaskRespDTO;
import marissaburca.TASK_TRACKER_BE.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getTasks (@AuthenticationPrincipal User loggedUser) {
        return taskService.getAllTasksForCurrentUser(loggedUser);
    }

    @GetMapping("/{taskId}")
    public Task getTaskById ( @PathVariable long taskId ) {
        return taskService.findById(taskId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskRespDTO saveTask ( @RequestBody @Validated TaskDTO payload, BindingResult validation,@AuthenticationPrincipal User loggedUser) throws BadRequest {
        if (validation.hasErrors()) {
            throw new BadRequest("Errors in validation " + validation.getAllErrors());
        } else {
            return taskService.saveTask(payload, loggedUser);
        }
    }


    @PutMapping("/{taskId}")
    public Task updateTask ( @PathVariable long taskId, @RequestBody Task editedTaskPayload ) {
        return taskService.findByIdAndUpdate(taskId, editedTaskPayload);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask ( @PathVariable long taskId ) {
        taskService.findByIdAndDelete(taskId);
    }
}
