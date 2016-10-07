package com.tapthis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.tapthis.entity.ReviewInfo;
import com.tapthis.entity.UserInfo;
import com.tapthis.service.ReviewService;
import com.tapthis.service.UserService;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebsiteController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ReviewService reviewService;
	
	//index
	@RequestMapping("/")
	public ModelAndView home(ModelAndView mv) {
		mv.setViewName("index");
		return mv;
 	}
	
	//GET one user by id from user table
	@RequestMapping(value="/user/{id}", method = RequestMethod.GET )
	public ResponseEntity<UserInfo> getUserById(@PathVariable("id") Integer id) {
		UserInfo user = userService.getUserById(id);
		return new ResponseEntity<UserInfo>(user, HttpStatus.OK);
	}

	//POST one user to user table
	@RequestMapping(value= "/user", method = RequestMethod.POST)
	public ResponseEntity<UserInfo> eachUser(@RequestBody UserInfo user, UriComponentsBuilder builder) {
        boolean flag = userService.addUser(user);
        if (flag == false) {
        	return new ResponseEntity<UserInfo>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/user/{id}").buildAndExpand(user.getUserId()).toUri());
        return new ResponseEntity<UserInfo>(headers, HttpStatus.CREATED);
	}
	
	//PUT one user to user table
	@RequestMapping(value="/user/{id}", method = RequestMethod.PUT )
	public ResponseEntity<UserInfo> updateUser(@RequestBody UserInfo user, @PathVariable("id") Integer userId) {
		userService.updateUser(user);
		return new ResponseEntity<UserInfo>(user, HttpStatus.OK);
	}
	
	//DELETE one user to user table
	@RequestMapping(value="/user/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<UserInfo> deleteUser(@PathVariable("id") Integer userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<UserInfo>(HttpStatus.NO_CONTENT);
	}	

	//GET all reviews by one beerName from review table
	@RequestMapping(value="/allreviewsbyname/{beerName}", method = RequestMethod.GET )
	public ResponseEntity<List<ReviewInfo>> getReviewByBeerName(@PathVariable("beerName") String beerName) {
		List<ReviewInfo> review = reviewService.getReviewByBeerName(beerName);
		return new ResponseEntity<List<ReviewInfo>>(review, HttpStatus.OK);
	}
	
	//GET all reviews by one reviewUserId from review table
	@RequestMapping(value="/allreviewsbyid/{reviewUserId}", method = RequestMethod.GET)
	public ResponseEntity<List<ReviewInfo>> getAllReviewsByUserId(@PathVariable("reviewUserId") int reviewUserId) {
		List<ReviewInfo> review = reviewService.getAllReviewsByUserId(reviewUserId);
		return new ResponseEntity<List<ReviewInfo>>(review, HttpStatus.OK);
	}
	
	//GET one review by one reviewUserId from review table
	@RequestMapping(value="/onereviewbyid/{reviewUserId}/{reviewId}", method = RequestMethod.GET)
	public ResponseEntity<List<ReviewInfo>> getOneReviewByUserId(@PathVariable("reviewUserId") int reviewUserId, @PathVariable("reviewId") int reviewId) {
		List<ReviewInfo> review = reviewService.getOneReviewByUserId(reviewUserId, reviewId);
		return new ResponseEntity<List<ReviewInfo>>(review, HttpStatus.OK);
	}

	//POST one review to review table
	@RequestMapping(value= "/review", method = RequestMethod.POST)
	public ResponseEntity<ReviewInfo> eachReview(@RequestBody ReviewInfo review, UriComponentsBuilder builder) {
        boolean flag = reviewService.addReview(review);
        if (flag == false) {
        	return new ResponseEntity<ReviewInfo>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/review/{id}").buildAndExpand(review.getReviewId()).toUri());
        return new ResponseEntity<ReviewInfo>(headers, HttpStatus.CREATED);
	}
	
	//PUT one review by reviewId to review table
	@RequestMapping(value="/review/{id}", method = RequestMethod.PUT )
	public ResponseEntity<ReviewInfo> updateReview(@RequestBody ReviewInfo review) {
		reviewService.updateReview(review);
		return new ResponseEntity<ReviewInfo>(review, HttpStatus.OK);
	}
	
	//DELETE one review by reviewId to review table
	@RequestMapping(value="/review/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<ReviewInfo> deleteReview(@PathVariable("id") Integer reviewId) {
		reviewService.deleteReview(reviewId);
		return new ResponseEntity<ReviewInfo>(HttpStatus.NO_CONTENT);
	}	
}
