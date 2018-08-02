package testapp.testing.com.mvppro.sayHello;

import java.util.List;

public interface SayHelloContract {
    /** Represents the View in MVP. */
    interface View {
        void showMessage(String message);

        void showError(String error);


        void showList(String stringList);


    }

    /** Represents the Presenter in MVP. */
    interface Presenter {
        void loadMessage();

        void saveName(String firstName, String lastName);


        void loadList();
    }}
