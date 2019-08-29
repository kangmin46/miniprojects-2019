package com.woowacourse.edd.repository;

import com.woowacourse.edd.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByVideo_IdAndLikeUser_Id(Long videoId, Long userId);
    Long countByVideo_Id(Long videoId);
}
