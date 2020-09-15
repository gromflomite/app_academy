package academy.model.DAO;

import academy.model.pojo.User;

public interface UserDAO {

    User exists (String user, String password);
    
}
