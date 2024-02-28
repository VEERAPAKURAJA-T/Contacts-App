package com.example.veera;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDAO {
    /*
    A Dao is an interface annotated with Dao annotation.
It specifies a contract for interacting with the database, including methods for inserting, updating,
deleting or querying data.I'll start by creating the first method void insert and I need to insert a contact object of type contacts.
So I need to pass contacts and I name it as contact.
This method is responsible for inserting data into a database table and I'll annotate it with insert
annotation and this annotation marks a method.
     */
    @Insert
        void insert(Contacts contacts);
    @Delete
     void delete (Contacts contacts);
    /*
    you can define custom SQL queries within the Dao methods using the Add query annotation So add query annotation and
    here you need to specify the SQL query.This allows you to execute complex database operations while
    still benefiting from rooms type safety and the query annotation allows you to define custom SQL queries and
    map the results to Java or Kotlin object. This method returning type should be a list of contact objects.
So list of contact objects that we have created
     */
    @Query("SELECT * FROM Contacts_table")
   LiveData<List<Contacts>> getAllContacts();
    /*
    Always use the live data by returning data as live data.
The room library ensures that the data is observed by the repository or ViewModel allowing for real
time updates when the underlying data changes.
     */
}
