package com.vidme.demo.repository;

import com.vidme.demo.model.Vids;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VidRepository extends JpaRepository<Vids, Long> {
    Vids findByTitle(String vidsTitle);
    List<Vids> findByUserId(Long userId);

    Vids findByIdAndUserId(Long vidsId, Long userId);

    Vids findByUserIdAndTitle(Long userId, String vidsTitle);
}
