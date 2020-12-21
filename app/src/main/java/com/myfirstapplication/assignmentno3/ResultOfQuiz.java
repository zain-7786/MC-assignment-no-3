package com.myfirstapplication.assignmentno3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ResultOfQuiz extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_of_quiz);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.calculator :
                        Toast.makeText(getApplicationContext(),"Calculator is clicked",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ResultOfQuiz.this, Calculator.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.myquiz :
                        Toast.makeText(getApplicationContext(),"My Quiz is clicked",Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(ResultOfQuiz.this, ComputerQuiz.class);
                        startActivity(intent1);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.assign3 :
                        Toast.makeText(getApplicationContext(),"Moving to the Start of the Activity",Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(ResultOfQuiz.this, MainActivity.class);
                        startActivity(intent2);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });
        boolean[] result = getIntent().getBooleanArrayExtra("result");
        String[] WrongOptions = getIntent().getStringArrayExtra("correctOptions");
        int myindex = 0;
        TextView textView = findViewById(R.id.myScore);
        final ArrayList<String> wrongMCQS = new ArrayList<String>();
        int count=0;
        for(int i=0;i<result.length;i++)
        {
            if(result[i])
            {
                count++;
            }
            else
            {
                String[] option = WrongOptions[myindex].split(":");
                myindex++;
                String val =  "MCQ no "+String.valueOf(i+1) +":  ----> \n";
                val += "You selected :  '"+option[0]+"'\n";
                val+="Right Answer : '"+option[1]+"'\n";
                wrongMCQS.add(val);
            }
        }
        textView.setText(count+"/10");
        ListView listView = findViewById(R.id.WrongMCQsList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,wrongMCQS);
        listView.setAdapter(arrayAdapter);
    }
}