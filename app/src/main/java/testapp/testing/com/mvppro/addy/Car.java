package testapp.testing.com.mvppro.addy;

public class Car
{
    String nameOfCar;
    String colorOfCar;

    public Car() {
    }

    public Car(String nameOfCar, String colorOfCar) {
        this.nameOfCar = nameOfCar;
        this.colorOfCar = colorOfCar;
    }


    public String getNameOfCar() {
        return nameOfCar;
    }

    public void setNameOfCar(String nameOfCar) {
        this.nameOfCar = nameOfCar;
    }

    public String getColorOfCar() {
        return colorOfCar;
    }

    public void setColorOfCar(String colorOfCar) {
        this.colorOfCar = colorOfCar;
    }
}
