package testapp.testing.com.mvppro.addy;

public class Car
{
    String nameOfCar;
    String colorOfCar;
    String image;

    public Car() {
    }

    public Car(String nameOfCar, String colorOfCar) {
        this.nameOfCar = nameOfCar;
        this.colorOfCar = colorOfCar;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
