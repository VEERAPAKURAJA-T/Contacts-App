package com.example.veera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.veera.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Data Source
    private ContactDatabase contactDatabase;
    private ArrayList<Contacts> contactsArrayList = new ArrayList<>();
   //Adapter
    private MyAdapter myAdapter;
    //Binding
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandlers handlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   //Data Binding
        mainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        /*
        Setcontentview is a method provided by Android's data binding library that
allows you to set the content view of an activity or a fragment with data binding enabled.
It simplifies the process of using data binding to bind UI elements to data sources in your layout XML
files.
         */
        handlers = new MainActivityClickHandlers(this);
        mainBinding.setClickHandler(handlers);
        //RecyclerView
        RecyclerView recyclerView=mainBinding.recyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //Database:
        contactDatabase= ContactDatabase.getInstance(this);
        //View Model:
        MyViewModel viewModel= new ViewModelProvider(this).get(MyViewModel.class);
        /*
        In this line, I am obtaining a reference to a recycler view widget from a layout that uses data binding
in Android.This is called main binding, so this is an object that uses and refers to this activity.
Underscore main.And I'm going to use a reference for the recycler view called recycler view here.
So this line is used to obtain a reference to a recycler view widget from a layout that uses data binding in Android.
This reference allows you to interact with and manipulate the recycler view programmatically in your
Android application code using the data binding library.Then let's move to create the adapter equal to new my adapter and
I'll pass contacts as an array list.Let's move to the database contact database equal to contact database dot get instance and I need to
pass the context get instance.As I told you before in the contact database, it will return one instance of the contact database because
we followed the singleton pattern.Then I need to use the view model.So I'll create my ViewModel instance.
ViewModel New ViewModel provider this dot get my view model dot class.So this line of code is used to get and create and retrieve an instance
of a ViewModel associated with a specific activity or fragment.Let's break it down.ViewModel Provider is a class provided by Android's
ViewModel architecture component.It's used to create and manage view models within the scope of an activity or fragment.
This keyword typically refers to the current activity or fragment instance in the context of your code.This is passed as an argument to the
view model provider.It signifies that you want to create or retrieve a ViewModel associated with the current activity or fragment.
The get method of ViewModel Provider is called with a class of the ViewModel you want.This method is responsible for creating a new instance
of the ViewModel if it doesn't already exist or returning an existing instance.
         */
        //Inserting a new Contact (Just For Testing):
        Contacts c1=new Contacts("veera","veera@gmail.com");//here the id value 1 is not recommended because of primary key.
        viewModel.addNewContact(c1);
        //Loading the data from ROOM DB
        /*
        the observe method and the observe method is used to observe or listen for changes
in the underlying data held by the live data object.
You use the observe method on a live data object and pass in an observer you created.
The Observe method establishes a connection between the live data and the observer.
It tells the live data to notify the observer when the data changed.
         */
        viewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {

            @Override
            public void onChanged(List<Contacts> contacts) {
                contactsArrayList.clear();
                /*
                every time without this line, every time I run the application or add the new contacts, there is a
duplication of items.So if my database contains five items, if I add one there should be six items.
But without this code, without clearing the array list, the array list will add five plus five plus
one.So it will display 11 items.
                 */
                for (Contacts c: contacts){
                 Log.v("TAGY",c.getName());
                 contactsArrayList.add(c);

             }
             myAdapter.notifyDataSetChanged();
             /*
             In order to display the data from logcat into the items of the recycler view, so notify data set.
Changed method is a method provided by recycler views adapter to notify the recycler view that are underlying
data has changed and the recycler view should refresh its views to reflect these changes.
This method is often used when you've made changes to the data resource and you need the recycler view
to display this change and you need to ensure that the UI is updated accordingly.
              */
            }
        });
        //Adapter
        myAdapter=new MyAdapter(contactsArrayList);

        //Linking the RecyclerView with the Adapter
        recyclerView.setAdapter(myAdapter);
    }
}