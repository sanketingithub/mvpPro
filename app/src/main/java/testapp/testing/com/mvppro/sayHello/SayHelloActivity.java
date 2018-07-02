package testapp.testing.com.mvppro.sayHello;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import testapp.testing.com.mvppro.R;
import testapp.testing.com.mvppro.addy.AddyActivity;

public class SayHelloActivity extends AppCompatActivity implements SayHelloContract.View, View.OnClickListener {

    private SayHelloContract.Presenter mPresenter;

    //UI properties
    private TextView mMessageView;
    private EditText mFirstNameView;
    private  EditText mLastNameView, editText3;

    strictfp float var = 00.2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this,AddyActivity.class));


        initViews();

        // Creates presenter
        mPresenter = new SayHelloPresenter(this);}
    private void initViews() {
        mMessageView = (TextView) findViewById(R.id.message);
        mFirstNameView = (EditText) findViewById(R.id.firstName);
        mLastNameView = (EditText) findViewById(R.id.lastName);



        editText3 = (EditText) findViewById(R.id.editText3);

        findViewById(R.id.update).setOnClickListener(this);
        findViewById(R.id.showMessage).setOnClickListener(this);

    }

    @Override
    public void showMessage(String message) {
        mMessageView.setText(message);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    /**
     * The View only receives user's action and leaves all remaining tasks for the Presenter
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update:
                mPresenter.saveName(mFirstNameView.getText().toString(),
                        mLastNameView.getText().toString());
                break;
            case R.id.showMessage:
                mPresenter.loadMessage();
                break;
        }
    }

    public void saveVal(View view)
    {

        setVal(editText3.getText().toString());

    }


    String val;
    private void setVal(String val)
    {
        this.val = val;
    }



    private void getVal(TextView textView) {
        if (val != null) {
            textView.setText(val);
        }

    startActivity(new Intent(this, AddyActivity.class));
    }



    public void showVal(View view)
    {
        getVal(mMessageView);
    }






}


/*MVP in Android
MVP is strongly recommended because a lot of developers are using it now. Even, Google also provide its best practice example on Github.

Definition is not always an interesting part but it is super important. We should even check it out again an again to understand it deeply.


Image Credit: Tin Megali
View = a passive interface that displays data and routes user actions to the Presenter. In Android, it is represented by Activity, Fragment, or View.
Model = a layer that holds business logic, controls how data is created, stored, and modified. In Android, it is a data access layer such as database API or remote server API.
Presenter = A middle man which retrieves data from Model and show it in the View. It also process user action forwarded to it by the View.
Important points of MVP are:

View can not access Model.
Presenter is tied to a single View.
View is completely dumb and passive (only retrieve user action and leave all other things for Presenter to handle).
*/