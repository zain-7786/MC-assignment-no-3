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
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class ComputerQuiz extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.computer_quiz);

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
                        Intent intent = new Intent(ComputerQuiz.this, Calculator.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.myquiz :
                        Toast.makeText(getApplicationContext(),"My Quiz is clicked",Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(ComputerQuiz.this, ComputerQuiz.class);
                        startActivity(intent1);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.assign3 :
                        Toast.makeText(getApplicationContext(),"Opening Welcome Page",Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(ComputerQuiz.this, MainActivity.class);
                        startActivity(intent2);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }

        });
    }

    public void GotoFirstMCQ(View view) {

        Intent intent = new Intent(this,MCQsInteraction.class);
        startActivity(intent);
    }
}