package testapp.testing.com.mvppro.addy;

import java.util.ArrayList;
import java.util.List;

public class AddyPresenter implements AddyContract.Presenter
{

    Car car;
    AddyContract.View view;

    public AddyPresenter(AddyContract.View view) {
        this.car = new Car();
        this.view = view;
    }

    @Override
    public void getCar() {

        if (car!=null)
        {
            String color =    car.getColorOfCar();
        String name =    car.getNameOfCar();

        view.showCarDetails(color,name);
        }

    }

    @Override
    public void setCar() {

        car.setColorOfCar("red");
        car.setNameOfCar("Ferrari");
    }
}
