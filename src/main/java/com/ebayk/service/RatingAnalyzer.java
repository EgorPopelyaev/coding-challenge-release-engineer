package com.ebayk.service;

import com.ebayk.data.user.User;
import com.ebayk.data.user.UserRating;
import com.ebayk.data.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

public class RatingAnalyzer {

  public static List<User> getRatedUserForRatingCreator(Integer userId)
      throws UserNotFoundException {

    List<User> ratedUsers = new ArrayList<User>();
    UserRepository userRepository = new UserRepository();
    User ratingCreator = userRepository.getUser(userId);

    if (ratingCreator != null) {
      for (User ratedUser : userRepository.getUsers()) {
        for (UserRating rating : ratedUser.getRatings()) {
          if (rating.getRatingCreatorUserId().equals(userId)) {
            ratedUsers.add(ratedUser);
          }
        }
      }
    } else {
      throw new UserNotFoundException(userId);
    }
    return ratedUsers;
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public static class UserNotFoundException extends Exception {

    public UserNotFoundException(Integer userId) {
      super("User with id '" + userId  + "' does not exist");
    }
  }
}