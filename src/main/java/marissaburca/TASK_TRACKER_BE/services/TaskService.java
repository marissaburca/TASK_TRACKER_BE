package marissaburca.TASK_TRACKER_BE.services;


import marissaburca.TASK_TRACKER_BE.entities.Task;
import marissaburca.TASK_TRACKER_BE.exceptions.NotFound;
import marissaburca.TASK_TRACKER_BE.payloads.task.TaskDTO;
import marissaburca.TASK_TRACKER_BE.payloads.task.TaskRespDTO;
import marissaburca.TASK_TRACKER_BE.repositories.TaskDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskDAO taskDAO;

    public List<Task> getAllTasks () {
        return taskDAO.findAll();
    }

    public Task findById ( long id ) {
        return taskDAO.findById(id).orElseThrow(() -> new NotFound(id));
    }

    public TaskRespDTO saveTask ( TaskDTO body ) {
        Task newTask = new Task();
        newTask.setTitle(body.title());
        newTask.setDescription(body.description());
        newTask.setDate(body.date());
        newTask.setTime(body.time());
        newTask.setStatus(body.status());
        Task savedTask = taskDAO.save(newTask);
        return new TaskRespDTO(savedTask.getId());
    }

    public Task findByIdAndUpdate ( long id, Task body ) {
        Task found = this.findById(id);
        found.setTitle(body.getTitle());
        found.setDescription(body.getDescription());
        found.setDate(body.getDate());
        found.setTime(body.getTime());
        found.setStatus(body.getStatus());
        return taskDAO.save(found);
    }

    public void findByIdAndDelete ( long id ) {
        Task found = this.findById(id);
        taskDAO.delete(found);
    }

}
