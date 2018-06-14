package testapp.testing.com.mvppro;

public class SayHelloPresenter implements SayHelloContract.Presenter{

    private Person mPerson;
    private SayHelloContract.View mView;

    public SayHelloPresenter( SayHelloContract.View view) {
        mPerson = new Person();
        mView = view;
    }

    @Override
    public void loadMessage() {
        if (mPerson.getFirstName() == null && mPerson.getLastName() == null){
            mView.showError("No person name found.");
            return;
        }

        String message = "Hi " + mPerson.getName() + "!";
        mView.showMessage(message);
    }

    @Override
    public void saveName(String firstName, String lastName) {
        mPerson.setFirstName(firstName);
        mPerson.setLastName(lastName);
    }
}