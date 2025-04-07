package Services;

import Dao.FeedbackDao;
import Models.Feedback.Feedback;

import java.sql.Date;
import java.util.List;

public class ServiceFeedback {
    private FeedbackDao feedbackDao = new FeedbackDao();
    public List<Feedback> getFeedbacksByProductId(int productId) {
    return feedbackDao.getFeedbacksByProductId(productId);
    }
    public int getIdOrderDetails(String idOrder, String idProduct){
        return feedbackDao.getIdOrderDetails(idOrder,idProduct);
    }
    public void insertReview(String comment, int idOrderDetail, int ratingRank, String status){
        feedbackDao.insertReview( comment,   idOrderDetail, ratingRank, status);
    }

}
