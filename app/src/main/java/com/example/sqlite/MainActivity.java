package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Dialog.DialogListener{

    private TextView textView;
    private EditText firstNameText, lastNameText, emailText;
    private Button insertButton, viewButton, updateButton, deleteButton;
    private String firstName, lastName, email;
    private DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        EditText firstNameText = (EditText) findViewById(R.id.editTextFirstName);
//        EditText lastNameText = (EditText) findViewById(R.id.editTextLastName);
//        EditText emailText = (EditText) findViewById(R.id.editTextEmail);
//
//        Button insertButton = (Button) findViewById(R.id.insertButton);
//        Button viewButton = (Button) findViewById(R.id.viewButton);
//        Button updateButton = (Button) findViewById(R.id.updateButton);
//        Button deleteButton = (Button) findViewById(R.id.deleteButton);
//
//        firstName = firstNameText.getText().toString();
//        lastName = lastNameText.getText().toString();
//        email = firstNameText.getText().toString();

    }

    public void insert(View view){
        firstNameText = (EditText) findViewById(R.id.editTextFirstName);
        lastNameText = (EditText) findViewById(R.id.editTextLastName);
        emailText = (EditText) findViewById(R.id.editTextEmail);

        insertButton = (Button) findViewById(R.id.insertButton);
        viewButton = (Button) findViewById(R.id.viewButton);
//        updateButton = (Button) findViewById(R.id.updateButton);
//        deleteButton = (Button) findViewById(R.id.deleteButton);

        firstName = firstNameText.getText().toString();
        lastName = lastNameText.getText().toString();
        email = emailText.getText().toString();
        Boolean checkInsertData = dbHelper.insertUserData(firstName,lastName, email);
        if(checkInsertData == true){
            Toast.makeText(MainActivity.this, "New entry inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "New entry not inserted", Toast.LENGTH_SHORT).show();

        }
    }

    public void update(View view){
        String firstName, lastName, email;

        firstName = firstNameText.getText().toString();
        lastName = lastNameText.getText().toString();
        email = firstNameText.getText().toString();
        Boolean checkInsertData = dbHelper.updateUserData(1, firstName,lastName, email);
        if(checkInsertData == true){
            Toast.makeText(MainActivity.this, "New entry inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "New entry not inserted", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete(View view) {
        openDialog();
//        firstName = firstNameText.getText().toString();
//
//        Boolean checkInsertData = dbHelper.deleteUserData(1);
//        if(checkInsertData == true){
//            Toast.makeText(MainActivity.this, "Entry deleted", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(MainActivity.this, "Entry is not deleted", Toast.LENGTH_SHORT).show();
//        }
    }

    public void view(View view){
        Cursor cursor = dbHelper.getData();
        if(cursor.getCount()==0){
            Toast.makeText(MainActivity.this, "No entries found", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext()){
            buffer.append("id: "+cursor.getString(0)+"\n");
            buffer.append("firstName: "+cursor.getString(1)+"\n");
            buffer.append("lastName: "+cursor.getString(2)+"\n");
            buffer.append("email: "+cursor.getString(3)+"\n");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("User Entries");
        builder.setMessage(buffer.toString());
        builder.show();
    }

    public void search(View view){
        openDialog();
    }

    public void openDialog(){
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "Enter id");
    }

    @Override
    public void applyTexts(String id) {
        Toast.makeText(this,id, Toast.LENGTH_SHORT).show();
    }
}