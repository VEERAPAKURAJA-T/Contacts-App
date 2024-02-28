package com.example.veera;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Contacts_table")
public class Contacts {
    /*
   Each entity class corresponds to one table and the fields properties or the variables within the entity
class represent INT columns in that table.Entities define the structure and the schema of your database tables.
As mentioned before, I have only one entity class called contacts, and I have inside this table three
columns, contact ID, contact name and contact email one of type int and two of type strings.
And by the way, you can specify various attributes within the entity annotation such as the table name,
primary keys and indices.You can specify it like contacts or name it whatever you want.
If you don't specify the table name here in the entity, the name of the table is specified exactly
as the name of the class.
     */
    @ColumnInfo(name ="contacts_id")
    @PrimaryKey(autoGenerate = true)
    private int id;
    /*
    We need to specify a primary key using a unique and primary key that it will not be repetitive and in
this case the contact underscore ID will act as a primary key.
So here in the ID field, I'll use an annotation called Primary Key and I'll specify autogenerate equal
to true.The primary key annotation indicates that the ID field is the primary key for this table.
The autogenerate equal to true attribute means that the SQL will automatically assign unique IDs to
the new records.
     */
   @ColumnInfo(name ="contacts_name")
    private String name;
   @ColumnInfo(name= "contacts_email")
    private String email;
   @Ignore
    public Contacts() {// this is used to prevent any null pointer exception.
    }

    public Contacts( String name, String email) {

        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
