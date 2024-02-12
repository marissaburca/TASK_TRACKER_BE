package marissaburca.TASK_TRACKER_BE.repositories;

import marissaburca.TASK_TRACKER_BE.entities.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarDAO extends JpaRepository<Avatar, Long> {
}
