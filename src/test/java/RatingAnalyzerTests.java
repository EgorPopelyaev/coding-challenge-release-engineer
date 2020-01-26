import com.ebayk.data.user.User;
import com.ebayk.service.RatingAnalyzer;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RatingAnalyzerTests {

    @Test
    public void getRatedUserForGivenRatingCreatorId() throws RatingAnalyzer.UserNotFoundException {
        List<User> ratedUsers = RatingAnalyzer.getRatedUserForRatingCreator(3);
        Assert.assertEquals(1, (int) ratedUsers.get(0).getId());
    }

    @Test(expected = RatingAnalyzer.UserNotFoundException.class)
    public void throwsUserNotFoundException_RatingCreatorNotExist() throws RatingAnalyzer.UserNotFoundException {
        RatingAnalyzer.getRatedUserForRatingCreator(33);
    }

    @Test(expected = RatingAnalyzer.UserNotFoundException.class)
    public void throwsUserNotFoundException_UserIdIsNull() throws RatingAnalyzer.UserNotFoundException {
        RatingAnalyzer.getRatedUserForRatingCreator(null);
    }
}
