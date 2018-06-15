package testapp.testing.com.mvppro.addy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import testapp.testing.com.mvppro.R;

public class AddyActivity extends Activity implements AddyContract.View {

    AddyContract.Presenter presenter;
  TextView textViewRes;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addy);

        presenter = new AddyPresenter(this);

      textViewRes = findViewById(R.id.textViewRes);
    }


    @Override
    public void showCarDetails(String name, String color) {

        textViewRes.setText("My Car is " + name + "  " + color);
    }

    @Override
    public void showError(String error) {

    }


    public void setCar(View view) {

      presenter.setCar();

  }

    public void getCar(View view) {

      presenter.getCar();
  }
}
