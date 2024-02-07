package marissaburca.TASK_TRACKER_BE.repositories;

import marissaburca.TASK_TRACKER_BE.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDAO extends JpaRepository<Task, Long> {
}
