package com.example.hunt.sqltesting;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class createSQLActivity extends ActionBarActivity {

    DBHelper mydb;



    private EditText nameEditTxt,surnameEditTxt;

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // check Fields For Empty Values
            checkFieldsForEmptyValues();
        }
    };

    void checkFieldsForEmptyValues(){
        Button b = (Button) findViewById(R.id.insert_button);

        String s1 = nameEditTxt.getText().toString();
        String s2 = surnameEditTxt.getText().toString();

        if(s1.equals("")|| s2.equals("")){
            b.setEnabled(false);
        } else {
            b.setEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sql);
        mydb = new DBHelper(this);
        nameEditTxt = (EditText) findViewById(R.id.insert_name);
        surnameEditTxt = (EditText) findViewById(R.id.insert_surname);

        nameEditTxt.addTextChangedListener(mTextWatcher);
        surnameEditTxt.addTextChangedListener(mTextWatcher);

        checkFieldsForEmptyValues();
    }

    public void insertShit(View view)
    {
        String _name = nameEditTxt.getText().toString();
        String _surname = surnameEditTxt.getText().toString();

        if(mydb.insertPerson(_name,_surname))
        {
            Context context = getApplicationContext();
            CharSequence text = "Insert OK!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            nameEditTxt.setText("");
            surnameEditTxt.setText("");
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_sql, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
