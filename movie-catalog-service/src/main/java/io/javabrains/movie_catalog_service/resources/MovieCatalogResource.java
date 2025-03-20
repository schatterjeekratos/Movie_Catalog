package io.javabrains.movie_catalog_service.resources;

import java.util.*;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import io.javabrains.movie_catalog_service.models.CatalogItem;
import io.javabrains.movie_catalog_service.models.Rating;
import io.javabrains.movie_catalog_service.models.Movie;
import io.javabrains.movie_catalog_service.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;
/*	@Autowired
	private WebClient.Builder webClientBuilder; */
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);

        return userRating.getRatings().stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
                })
                .collect(Collectors.toList()); 
	}
}
/*	List<Rating> ratings = Arrays.asList(
				new Rating("yuegwfgywe", 4),
				new Rating("uewkjd", 3)
				);
	return ratings.stream().map(rating -> {
	Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+ rating.getMovieId(), Movie.class);
	 return new CatalogItem(movie.getName(),"test",rating.getRating());
	})
		.collect(Collectors.toList());
		*/

		/*
		 return ratings.stream().map(rating -> {
	      Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);  
			/*Movie movie = webClientBuilder.build()
			 .get()
			 .uri("http://localhost:8082/movies/" + rating.getMovieId())
			 .retrieve()
			 .bodyToMono(Movie.class)
			 .block();	
			 */

