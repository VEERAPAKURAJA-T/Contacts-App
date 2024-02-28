package com.example.veera;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    //If you need to use context inside your ViewModel
    //you should use AndroidViewModel(AVM),
    //because it contains the application context.
    private Repository myRepository;
    //LiveData
    private LiveData<List<Contacts>> allContacts;

    public MyViewModel(@NonNull Application application) {
        super(application);
        myRepository=new Repository(application);
    }
    /*
    AndroidViewModel class is a subclass of ViewModel and
    Similar to them ,they are designed to store and manage UI-
    Related data are responsible to prepare&provide data for UI and
    automatically allow data to survive configuration change.
     */
    public LiveData<List<Contacts>> getAllContacts(){
        allContacts= myRepository.getAllContacts();
        return allContacts;
    }
    public void addNewContact(Contacts contact){
        myRepository.addContact(contact);
    }
    public void deleteContact(Contacts contacts){
        myRepository.deleteContact(contacts);
    }
    /*
    Live data is used to observe changes in the user data and automatically update the UI when data changes
and contacts can be inserted into room database through the ViewModel and also be deleted.
This Mvvm architecture separates concerns, makes the code more maintainable and ensures a responsive
and up to date UI.The ViewModel acts as a bridge between the UI and the data source, which is the room database in our
application, making it easier to manage data and UI interactions.
     */
}
