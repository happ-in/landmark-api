package com.landmark.app.model.repository.support;

import com.landmark.app.model.domain.comment.QnaComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface QnaCommentRepository extends JpaRepository<QnaComment, Integer> {



    List<QnaComment> findAllByQnaId(int qnaId);
    QnaComment findById(int id);

    void deleteById(int id);

    @Modifying
    @Transactional
    int deleteByQnaId(int qnaId);

    /*
    @Transactional
    @Modifying
    @Query("update QNA_COMMENT c set " +
            "c.comment=:comment, c.modifiedTime=:modifiedTime " +
            "where c.id=:id and c.userId=:userId and c.qnaId=:qnaId")
    void updateQnaCommentByIdUserId(@Param("comment") String comment, @Param("modifiedTime") Date modifiedTime,
                                    @Param("id") int id, @Param("userId") int userId, @Param("qnaId") int qnaId);

     */
}
