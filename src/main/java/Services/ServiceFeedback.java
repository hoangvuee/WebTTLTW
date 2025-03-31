package Services;

import Dao.FeedbackDao;
import Models.Feedback.Feedback;

import java.util.List;

public class ServiceFeedback {
    private FeedbackDao feedbackDao = new FeedbackDao();
    public List<Feedback> getFeedbacksByProductId(int productId) {
    return feedbackDao.getFeedbacksByProductId(productId);
    }
}
