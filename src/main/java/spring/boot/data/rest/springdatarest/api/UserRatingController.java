package spring.boot.data.rest.springdatarest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.boot.data.rest.springdatarest.domain.User;
import spring.boot.data.rest.springdatarest.domain.UserRating;
import spring.boot.data.rest.springdatarest.domain.UserRatingPk;
import spring.boot.data.rest.springdatarest.dto.RatingDto;
import spring.boot.data.rest.springdatarest.repository.UserRatingRepository;
import spring.boot.data.rest.springdatarest.repository.UserRepository;

import java.util.AbstractMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/{userId}/ratings")
public class UserRatingController {

    private UserRatingRepository userRatingRepository;
    private UserRepository userRepository;

    @Autowired
    public UserRatingController(UserRatingRepository userRatingRepository, UserRepository userRepository) {
        this.userRatingRepository = userRatingRepository;
        this.userRepository = userRepository;
    }

    protected UserRatingController() {
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUserRating(@PathVariable("userId") Long userId, @RequestBody @Validated RatingDto ratingDto) {
        User user = verifyUser(userId);
        userRatingRepository.save(new UserRating(new UserRatingPk(user, ratingDto.getCustomerId()), ratingDto.getScore(), ratingDto.getComment()));
    }

    @GetMapping
    public List<RatingDto> getAllRatingsForUser(@PathVariable("userId") Long userId) {
        verifyUser(userId);
        return userRatingRepository.findByPkUserId(userId).stream().map(userRating -> toDto(userRating)).collect(Collectors.toList());
    }

    @GetMapping("/pageable")
    public Page<RatingDto> getAllRatingsForUser(@PathVariable("userId") Long userId, Pageable pageable) {
        verifyUser(userId);
        Page<UserRating> userRatingPage = userRatingRepository.findByPkUserId(userId, pageable);
        List<RatingDto> ratingDtos = userRatingPage.getContent().stream().map(userRating -> toDto(userRating)).collect(Collectors.toList());
        return new PageImpl<RatingDto>(ratingDtos, pageable, userRatingPage.getTotalPages());
    }

    @GetMapping("/average")
    public AbstractMap.SimpleEntry<String, Double> getAverage(@PathVariable("userId") Long userId) {
        verifyUser(userId);
        List<UserRating> userRatings = userRatingRepository.findByPkUserId(userId);
        OptionalDouble average = userRatings.stream().mapToInt(UserRating::getScore).average();
        return new AbstractMap.SimpleEntry<String, Double>("average", average.isPresent() ? average.getAsDouble() : null);
    }


    public User verifyUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("user does not exist: " + userId));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    private String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }

    private RatingDto toDto(UserRating userRating) {
        return new RatingDto(userRating.getScore(), userRating.getComment(), userRating.getPk().getCustomerId());
    }
}
