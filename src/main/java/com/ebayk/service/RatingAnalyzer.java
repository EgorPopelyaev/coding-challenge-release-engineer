package com.ebayk.service;

import com.ebayk.data.user.User;
import com.ebayk.data.user.UserRating;
import com.ebayk.data.user.UserRepository;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class RatingAnalyzer {

  public static List<User> getRatedUserForRatingCreator(Integer userId)
      throws UserNotFoundException {

    List<User> ratedUsers = new ArrayList<User>();
    UserRepository userRepository = new UserRepository();
    User ratingCreator = userRepository.getUser(userId);

    if (ratingCreator != null) {
      for (User ratedUser : userRepository.getUsers()) {
        for (UserRating rating : ratedUser.getRatings()) {
          if (rating.getRatingCreatorUserId().equals(ratingCreator.getId())) {
            User user = userRepository.getUser(rating.getRatingCreatorUserId());
            if (user != null) {
              ratedUsers.add(ratingCreator);
            } else {
              throw new UserNotFoundException(rating.getRatingCreatorUserId());
            }
          }
        }
      }
    } else {
      throw new UserNotFoundException(userId);
    }
    return ratedUsers;
  }

  public static class UserNotFoundException extends Exception {

    public UserNotFoundException(Integer userId) {
      super("User with id '" + userId  + "' does not exist");
    }
  }
}