package venesysproject.com.venesysproyecto;

import android.app.Application;

public class SessionSecundaryUser extends Application {

    private User user;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
