package io.javabrains.ratings_data_service.resource;

import io.javabrains.ratings_data_service.model.Rating;

import java.util.Arrays;
import java.util.List;

import io.javabrains.ratings_data_service.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {

    @RequestMapping("/{movieId}")
    public Rating getMovieRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }
  /*  
    @RequestMapping("/user/{userId}")
    public List<Rating> getUserRatings(@PathVariable("userId") String userId) {
    	List<Rating> ratings = Arrays.asList(
				new Rating("yuegwfgywe", 4),
				new Rating("uewkjd", 3)
				);
    	return ratings;
    } */
  @RequestMapping("/user/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
        UserRating userRating = new UserRating();
        userRating.initData(userId);
        return userRating;

    }

}