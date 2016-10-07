package com.tapthis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	
	@RequestMapping("/APIResults")
	public ModelAndView APIResults(ModelAndView mv) {
		return mv;
 	}
	
	@RequestMapping("/beerDetails")
	public ModelAndView beerDetails(ModelAndView mv) {
		return mv;
 	}
	
	//view user by id from database
	@RequestMapping(value="/user/{id}", method = RequestMethod.GET )
	public ResponseEntity<UserInfo> getUserById(@PathVariable("id") Integer id) {
		UserInfo user = userService.getUserById(id);
		return new ResponseEntity<UserInfo>(user, HttpStatus.OK);
	}

	//view all users from database
	@RequestMapping(value= "/user", method = RequestMethod.GET)
	public ResponseEntity<List<UserInfo>> getUsers() {
		List<UserInfo> user = userService.getUsers();
		return new ResponseEntity<List<UserInfo>>(user, HttpStatus.OK);
	}

	//write to user database
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
	
	//update to user database
	@RequestMapping(value="/user/{id}", method = RequestMethod.PUT )
	public ResponseEntity<UserInfo> updateUser(@RequestBody UserInfo user, @PathVariable("id") Integer userId) {
		userService.updateUser(user);
		return new ResponseEntity<UserInfo>(user, HttpStatus.OK);
	}
	
	//delete to user database
	@RequestMapping(value="/user/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<UserInfo> deleteUser(@PathVariable("id") Integer userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<UserInfo>(HttpStatus.NO_CONTENT);
	}	
	
	//view reviews by id from database
	@RequestMapping(value="/review/{id}", method = RequestMethod.GET )
	public ResponseEntity<ReviewInfo> getReviewById(@PathVariable("id") Integer id) {
		ReviewInfo review = reviewService.getReviewById(id);
		return new ResponseEntity<ReviewInfo>(review, HttpStatus.OK);
	}

	//view all reviews from database
	@RequestMapping(value= "/review", method = RequestMethod.GET)
	public ResponseEntity<List<ReviewInfo>> getReviews() {
		List<ReviewInfo> review = reviewService.getReviews();
		return new ResponseEntity<List<ReviewInfo>>(review, HttpStatus.OK);
	}

	//write review to database
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
	
	//update review to database
	@RequestMapping(value="/review/{id}", method = RequestMethod.PUT )
	public ResponseEntity<ReviewInfo> updateReview(@RequestBody ReviewInfo review) {
		reviewService.updateReview(review);
		return new ResponseEntity<ReviewInfo>(review, HttpStatus.OK);
	}
	
	//delete review from database
	@RequestMapping(value="/review/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<ReviewInfo> deleteReview(@PathVariable("id") Integer reviewId) {
		reviewService.deleteReview(reviewId);
		return new ResponseEntity<ReviewInfo>(HttpStatus.NO_CONTENT);
	}	
}
