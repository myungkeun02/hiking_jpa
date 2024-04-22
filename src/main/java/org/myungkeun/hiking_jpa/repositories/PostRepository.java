package org.myungkeun.hiking_jpa.repositories;


import org.myungkeun.hiking_jpa.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
