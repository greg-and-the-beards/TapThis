package com.tapthis.dao;

import java.util.List;

import com.tapthis.entity.ReviewInfo;

public interface ReviewDAO {
	
	List<ReviewInfo> getReviews();
	List<ReviewInfo> getReviewByBeerName(String beerName);
	List<ReviewInfo> getAllReviewsByUserId(int reviewUserId);
	List<ReviewInfo> getOneReviewByUserId(int reviewUserId, int reviewId);
	ReviewInfo getReviewById(int reviewId);
	boolean addReview(ReviewInfo reviewId);
	void updateReview(ReviewInfo reviewId);
	void deleteReview(int reviewId);

}
