package com.example.veera;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    /*
    we have only the room database, the offline database.
So for that I'll create private final contact Dao and I'll use and I name it as contact Dao object.
So when dealing with room databases, you need to deal with the contact dao in the repository.
     */
    private final ContactDAO contactDAO;
    ExecutorService executor;
    Handler handler;
    public Repository(Application application) {
        ContactDatabase contactDatabase=ContactDatabase.getInstance(application);
        this.contactDAO = contactDatabase.getContactDAO();
         executor = Executors.newSingleThreadExecutor();
         handler =new Handler(Looper.getMainLooper());
    }
    /*
    This repository encapsulates data access and retrieval logic.
It receives a contact Dao object in its constructor, which provides access to the local database,
assuming you are using room.And this is what we've done and every method created in the Dao, you should mention it here in the
repository.
     */
    public void addContact(Contacts contacts){

        /*
        Why we use handlers while database operations occur in the background, you often need to update the
UI with the results of these operations.However, UI updates must be performed on the main UI thread to avoid view related issues.
The handler with Looper dot get main Looper method is used to post tasks to the main UI threads messages queue.
This ensures that any UI related code such as updating text views or recycler views is executed on the main thread.
So we need to execute the add context in the background.So I use the executor service.I need to update the UI, the text views or the recycler view.
So for that I use the handler with Looper by combining an executor service with the handler.This is for background database
operations and handler for UI updates. You achieve many benefits such as thread separation synchronization and responsive UI thread separation
background Database operations are separated from UI updates, preventing UI blocking and an application not responding errors.
Synchronization. Concurrent access to the database is managed by executer service, ensuring that database operations
occur sequentially and avoiding race condition.Responsive UI updates are performed on the main thread using the handler, ensuring a responsive use
interface.I need to use the executor to execute this method in the background and I'll use the handler to update the UI.
So for that I need to use the executor dot execute method.And here I need to use a new runnable and you can see I override it.The run method.
         */
       // contactDAO.insert(contacts);
        //Runnable:Executing Tasks on separate Thread
/*
Method Runnable is an interface that defines a task to be executed.Typically on a separate thread.
It represents a unit of work that can be executed as synchronously.Runnable objects are commonly used with thread related APIs,
including executor service to perform background tasks, and the runnable interface defines a single method void run that is executed when
the runnable task is Executed.Runnable objects are used to encapsulate code that you want to run asynchronously on a separate thread
By submitting a runnable to an executor service, you delegate the execution of that task to the thread managed by the executor.
So in order to execute this code in the background, we need to create an executor which is used for background database operations, executor service.
Then we need to use the handler for used for the updating the UI and linking this executor with runnable is used to provide the run method that will
execute this code asynchronously on separate thread.So this is how in Java we perform background tasks.
 */
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.insert(contacts);
            }
        });
    }
    public void deleteContact(Contacts contacts){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.delete(contacts);
            }
        });
      //  contactDAO.delete(contacts);
    }
    public LiveData<List<Contacts>> getAllContacts(){
        /*
        If we look at the run method of the runnable, we can see that the returning type is void.This means that it will return nothing.
If we go to get all contacts method.We can't execute this in the background because at first the run will not return anything and I need
to return a list of contacts object.And the second reason is that I need to publish contacts to get all contacts and publish it to the UI.
So this is will be executed in the background.So for that I'll use the live data.Live data is often used in both the repository and the Dao
to facilitate the observation of database changes and provide real time updates to the UI.
         */
        return contactDAO.getAllContacts();
    }
    /*
    Room database operations such as insertions, updates and queries should not be executed on the main
UI thread because they can potentially block the UI causing the app to become unresponsive.
We need to offload these database operations to background threads.
By doing this, you keep the UI thread free to handle user interactions and ensure that your app remains
responsive. we move to the Kotlin sections, we will use the coroutines that will do these operations in the
background.But in Java we need to use handler and executer service in order to execute the methods in the background.
So I can't write like this.Add contact and call the contact Dao insert method directly because this will be executed in the main
UI thread making problems for our application and cause A and R application not responding or maybe crashes.
So to prevent any possible error we need to use handlers and the executer services in order to do thisnjob or this operation.
In the background I'll use executer service, I'll name it as executor and alt+ enter to import this class equals to
executer dot new single thread executer and executer service.Usually a thread pool executor is used to offload these database operations
to background threads.By doing this, you keep the UI thread free to handle user interactions and ensure that your app remains
responsive as single threaded executer is created, meaning that database operation will be executed
sequentially in a background thread.This can help avoid concurrency issues when dealing with database access.
     */

}
/*
Live data is typically used to expose data from the room database to the ViewModel and ultimately to the UI components, activities or fragments.
The repository interacts with the room database to retrieve data and it wraps the data in a live data object before passing it to the ViewModel.
By using the live data, the repository ensures that the data can be observed by the ViewModel, and any changes in the underlying data are
automatically reflected in the UI without the need to for explicit updates.
 */
