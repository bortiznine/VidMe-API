package com.vidme.demo.repository;

import com.vidme.demo.model.Vids;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VidRepository extends JpaRepository<Vids, Long> {
    Vids findByTitle(String vidsTitle);
    List<Vids> findByUserId(Long userId);

    //find by user and category then return the category
    Vids findByIdAndUserId(Long vidsId, Long userId);

    //find by user id and category name
    Vids findByUserIdAndTitle(Long userId, String vidsTitle);
}
