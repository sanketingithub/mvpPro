package testapp.testing.com.mvppro;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import testapp.testing.com.mvppro.addy.Car;

public class RecyclerViewActivity extends Activity {
    // ArrayList for person names
    ArrayList personNames = new ArrayList<>(Arrays.asList("Person 1", "Person 2", "Person 3", "Person 4", "Person 5", "Person 6", "Person 7"));

    ArrayList<Car> carArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        // get the reference of RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView recyclerViewGrid = findViewById(R.id.recyclerViewGrid);
        RecyclerView recyclerViewStaggered = findViewById(R.id.recyclerViewStaggered);

        addCars();
          //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter(this, carArrayList);





        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView


        // set a GridLayoutManager with default vertical orientation
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerViewGrid.setLayoutManager(gridLayoutManager);
        recyclerViewGrid.setAdapter(customAdapter); // set the Adapter to RecyclerView


        // set a LinearLayoutManager with default vertical orientation
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL);
        recyclerViewStaggered.setLayoutManager(staggeredGridLayoutManager);
        recyclerViewStaggered.setAdapter(customAdapter); // set the Adapter to RecyclerView




    }


    void  addCars()
    {
        carArrayList = new ArrayList<>();
        Car car = new Car();
        car.setNameOfCar("sdasdas");
        car.setImage("http://mnmgirls.us/wp-content/uploads/2018/02/png-pictures-fire-and-ice-round-light-effect-dynamic-png-image-for-free-download-ideas.jpg");

      Car car1 = new Car();
        car1.setNameOfCar("sdasdas");
        car1.setImage("https://media.glassdoor.com/sqll/9079/google-squarelogo-1441130773284.png");

      Car car2 = new Car();
        car2.setNameOfCar("sdasdas");
        car2.setImage("http://www.floramoments.com/images/fb.png");

        carArrayList.add(car);
        carArrayList.add(car1);
        carArrayList.add(car2);

       carArrayList.add(car);
        carArrayList.add(car1);
        carArrayList.add(car2);

       carArrayList.add(car);
        carArrayList.add(car1);
        carArrayList.add(car2);

       carArrayList.add(car);
        carArrayList.add(car1);
        carArrayList.add(car2);

       carArrayList.add(car);
        carArrayList.add(car1);
        carArrayList.add(car2);

       carArrayList.add(car);
        carArrayList.add(car1);
        carArrayList.add(car2);

       carArrayList.add(car);
        carArrayList.add(car1);
        carArrayList.add(car2);

       carArrayList.add(car);
        carArrayList.add(car1);
        carArrayList.add(car2);


    }





}
