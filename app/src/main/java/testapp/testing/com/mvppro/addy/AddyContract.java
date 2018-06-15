package testapp.testing.com.mvppro.addy;

import java.util.List;

public interface AddyContract {

    interface View {
        void showCarDetails(String name,String color);

        void showError(String error);
    }

    /** Represents the Presenter in MVP. */
    interface Presenter {
       void getCar();

        void setCar();
    }

}
