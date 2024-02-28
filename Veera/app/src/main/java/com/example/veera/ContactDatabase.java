package com.example.veera;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Contacts.class,version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    /*
    The database class is an abstract class that serves as the database holder.
It includes methods to access the daos and create a database instance.
Let's create this database class and I'll name it as contact database.
You annotate the database class with the Add database annotation and specify the list of entities it
includes and the database version.I'll create it as an abstract, as mentioned before, to prevent any possible creation of this class
so you prevent possible creation of objects extends.
And here I need to tell Android studio that I need to extend from the room database so this class will
act as a database instance using the annotation, add database and extending room database from Android
X room package.
     */
    public abstract ContactDAO getContactDAO();
    //Here I am linking this database with Dao by using this method.
    /*
    The second step in creating our database is to follow the singleton pattern, to provide one instance
during the life cycle of the app.A database instance can be resource intensive and creating multiple instances of the database unnecessarily
consumes memory and processing power.A singleton ensures that only one instance of the database exists throughout the application's life
cycle optimizing resource usage.So for that I'll create a private static an instance from this contact database and I'll call it as
instance DB instance.
     */
    private static ContactDatabase dbInstance;
    public static synchronized ContactDatabase getInstance(Context context){//This method is used to provide a contact database when it's called.
                                                                            // synchronized is used to prevent any cleaning of the contact database.
        /*
          I need to check if there is any existing instance of the database during the life cycle of the app,
then I'll provide it.Otherwise I'll create a new instance of the room database.
So if instance this DB instance equals to null, so there is no instance of the database.
I need to create a new room instance.
         */
  //  if(dbInstance==null){
 //    dbInstance=Room.databaseBuilder( context.getApplicationContext(),
    //         ContactDatabase.class,
    //         "contacts_db")
   /*
   This is a factory method provided by the room library to create a new database or access an existing
one.It allows you to configure and build a room database instance Context Dot get application Context.
Context is typically the context of the application component, like an activity or application.
And here get application context method is used to obtain the application context, which is often preferred
when working with databases because it has a longer life cycle than, for example, an activity context.
    */
        if(dbInstance==null){
            dbInstance=Room.databaseBuilder( context.getApplicationContext(),
                    ContactDatabase.class,
                    "contacts_db").fallbackToDestructiveMigration().build();
            /*
            call fall back to destructive migration.
This method is used to specify a database migration strategy.In this case, this method is used.
It means that if a new version of the database schema is detected due to changes in the entity structure
room will drop and recreate the database effectively discarding all existing data.
This is called destructive migration and is useful during development, or when it's acceptable to lose
existing data for production apps.You typically implement more sophisticated migration strategies to prevent data during or to preserve
data during schema changes.
And finally, the build method is called to build the room database instance according to the specified
configuration.
             */

    }
        return dbInstance;
        /*
        This singleton pattern helps maintain consistency, thread, safety and efficient resource utilization
when working with a room database.
         */
    }
}
