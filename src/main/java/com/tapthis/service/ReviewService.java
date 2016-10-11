package com.tapthis.service;

import java.util.List;

import com.tapthis.entity.ReviewInfo;

public interface ReviewService {

	List<ReviewInfo> getReviews();

	ReviewInfo getReviewById(int reviewId);

	boolean addReview(ReviewInfo reviewId);

	void updateReview(ReviewInfo reviewId);

	void deleteReview(int reviewId);
}
