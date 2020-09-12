package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.model.Tag;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment getComment(int id) {
        return commentRepository.findComment(id);
    }

    public Comment createComment(Comment comment) {
        return commentRepository.createComment(comment);
    }

    public List<Comment> getImageComments(int imageId) { return commentRepository.findCommentsByImageId(imageId); }

    public void deleteImageComments(List<Comment> comments) { commentRepository.deleteImageComments(comments); }
}
