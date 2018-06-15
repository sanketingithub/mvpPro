package testapp.testing.com.mvppro.sayHello;

public class Person {
    private String mFirstName;
    private String mLastName;

    public Person() {
    }

    public Person(String firstName, String lastName) {
        mFirstName = firstName;
        mLastName = lastName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public String getName() {
        return mFirstName + " " + mLastName;
    }
}
