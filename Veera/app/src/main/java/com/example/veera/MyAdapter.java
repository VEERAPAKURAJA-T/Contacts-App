package com.example.veera;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veera.databinding.ContactListItemBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactViewHolder> {
    private ArrayList<Contacts> contacts;

    public MyAdapter(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //  Creating new view holder for items in recyclerView
        /*
        Data binding util dot inflate method is provided by the Android's data binding library Android X data
binding for inflating a layout and creating a binding object.
         */
        ContactListItemBinding contactListItemBinding=
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.contact_list_item,parent,false
                );
        return new ContactViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
      //Called by recyclerView when it needs to display or update an item
        //at a specific position in the list or grid
       // holder.name.setText(here it is not use because we use data binding.
          Contacts currentContact = contacts.get(position);

          holder.contactListItemBinding.setContacts(currentContact);
    }

    @Override
    public int getItemCount() {
        if(contacts!=null) {
            return contacts.size();
        }
        else {
            return 0;
        }
    }
    public void SetContacts(ArrayList<Contacts> contacts){
        this.contacts=contacts;
        /*
        Inform the associated RecyclerView that the underlying dataset has changed,
        and the RecyclerView should refresh its views to reflect these changes.

         */
        notifyDataSetChanged();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{
      //  private TextView name,email; here this is we don't use because we get the data by live data binding.
        private ContactListItemBinding contactListItemBinding;

        public ContactViewHolder(@NonNull ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            /*
            this method get route is a method used
to obtain the route view of the layout associated with a data binding instance.
It returns the route view of the layout associated with that binding object and in other words, it
provides access to the top level view in your layout XML file.
             */
            this.contactListItemBinding = contactListItemBinding;
        }
    }
}
