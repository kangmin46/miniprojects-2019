package com.woowacourse.edd.repository;

import com.woowacourse.edd.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
