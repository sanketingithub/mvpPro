package testapp.testing.com.mvppro.sayHello;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import testapp.testing.com.mvppro.sayHello.Person;
import testapp.testing.com.mvppro.sayHello.SayHelloContract;

public class SayHelloPresenter implements SayHelloContract.Presenter{

    private Person mPerson;
    private SayHelloContract.View mView;

    Context context;

    public SayHelloPresenter( SayHelloContract.View view) {
        mPerson = new Person();
        mView = view;
        context = (Context) view;
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

    @Override
    public void loadList() {

        String urlJsssss = "https://api.myjson.com/bins/tdf2i";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlJsssss, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                mView.showList(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mView.showList(error.getMessage());

            }
        });

        Volley.newRequestQueue(context).add(stringRequest);


    }













}