package com.tapthis.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.tapthis.entity.ReviewInfo;


@Repository
public class ReviewDAOImpl implements ReviewDAO {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public 	ReviewInfo getReviewById(int reviewId) {
		return hibernateTemplate.get(ReviewInfo.class, reviewId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReviewInfo> getReviews() {
		String hql = "FROM ReviewInfo as e ORDER by e.reviewId";
		return (List<ReviewInfo>) hibernateTemplate.find(hql);
	}
	
	@Override
	public boolean addReview(ReviewInfo review) {
		hibernateTemplate.save(review);
		return false;
	}
	
	@Override
	public void updateReview(ReviewInfo review) {
		ReviewInfo record = getReviewById(review.getReviewId());
		
		record.setBeerName(review.getBeerName());
		record.setReviewUserId(review.getReviewUserId());
		record.setBeerRating(review.getBeerRating());
		record.setHopsRating(review.getHopsRating());
		record.setMaltRating(review.getMaltRating());
		record.setReviewComment(review.getReviewComment());
		record.setReviewAdded(review.getReviewAdded());
		
		hibernateTemplate.update(record);
	}
	
	@Override
	public void deleteReview(int reviewId) {
		hibernateTemplate.delete(getReviewById(reviewId));
	}
}
