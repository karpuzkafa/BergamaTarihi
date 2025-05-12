package com.bergama.tarih;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView nv_side;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Drawer kurulumları
        drawerLayout = findViewById(R.id.main);
        nv_side = findViewById(R.id.nv_side);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }



        // Menü itemleri dinleyicisi
        nv_side.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            int index = 0;
            boolean flag = true;

            if (id == R.id.nav_github) {
                flag = false;

                String url = "https://github.com/karpuzkafa/BergamaTarihi";
                try {
                    Intent githubIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(githubIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Tarayıcı bulunamadı", Toast.LENGTH_SHORT).show();
                }

            }
            else if (id == R.id.menu_genel) {index = 1;}
            else if (id == R.id.menu_akropol) {index = 2;}
            else if (id == R.id.menu_asklepion) {index = 3;}
            else if (id == R.id.menu_allianoi) {index = 4;}
            else if (id == R.id.menu_diger) {index = 5;}
            else if (id == R.id.menu_dunya_miras) {index = 6;}
            else if (id == R.id.menu_tarih) {index = 7;}

            if (flag){
                Intent intent = new Intent(MainActivity.this, SlideActivity.class);
                intent.putExtra("slide", index);
                startActivity(intent);
            }

            drawerLayout.closeDrawers();
            return true;
        });


        MaterialButton github_page = findViewById(R.id.main_github);
        github_page.setOnClickListener(v -> {
                String url = "https://github.com/karpuzkafa/BergamaTarihi";
        try {
            Intent githubIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(githubIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(MainActivity.this, "Tarayıcı bulunamadı", Toast.LENGTH_SHORT).show();
        }
        });


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

