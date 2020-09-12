package ImageHoster.repository;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class CommentRepository {
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    public Comment findComment(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Comment> typedQuery = em.createQuery("SELECT c from Comment c where c.id =:id", Comment.class).setParameter("id", id);
            return typedQuery.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Comment> findCommentsByImageId(int imageId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Comment> typedQuery = em.createQuery("SELECT c from Comment c where c.image.id =:image_id", Comment.class).setParameter("image_id", imageId);
            return typedQuery.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public Comment createComment(Comment comment) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(comment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return comment;
    }

    public void deleteImageComments(List<Comment> comments) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        for(Comment x : comments) {
            try {
                transaction.begin();
                Comment comment = em.find(Comment.class, x.getId());
                em.remove(comment);
                transaction.commit();
            }
            catch (Exception e) {
                transaction.rollback();
            }
        }
    }
}
