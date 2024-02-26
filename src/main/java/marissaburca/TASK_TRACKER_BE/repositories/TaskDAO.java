package marissaburca.TASK_TRACKER_BE.repositories;

import marissaburca.TASK_TRACKER_BE.entities.Task;
import marissaburca.TASK_TRACKER_BE.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskDAO extends JpaRepository<Task, Long> {
    List<Task> findByUser( User user);
}
