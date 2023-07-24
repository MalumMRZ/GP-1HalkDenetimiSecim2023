package com.yusufmirza.halkdenetimisecim2023;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    ChestAdapter chestAdapter;
    TinyDB tinyDB;
    Button upload;

    EditText chestNo;

    boolean db= true;
    RecyclerView recyclerView;
    ArrayList<Chest> chestArrayList;
    FloatingActionButton floatingActionButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        upload = findViewById(R.id.buttonUpload);
        tinyDB = new TinyDB(this);


        /*
            ArrayList<Object> chestList = tinyDB.getListObject("chestList", Chest.class);
            ArrayList<Chest> chestArrayList1 = new ArrayList<>();
            if (chestList != null) {
                for (Object chest : chestList) {
                    chestArrayList1.add((Chest) chest);
                }
                chestArrayList = chestArrayList1;
                Chest.setChestList(chestArrayList);
                setRecyclerView(chestArrayList);

            }
        */

        if (Chest.getChestList() != null) {
            chestArrayList = Chest.getChestList();
            setRecyclerView(chestArrayList);
        }


        if (chestArrayList.size() == 0) {
            chestArrayList = new ArrayList<Chest>();
            Toast.makeText(this, "Lütfen sandık ekleyiniz---Önceden kaydettiyseniz lütfen Yükleyiniz", Toast.LENGTH_LONG).show();

        } else {
            setRecyclerView(chestArrayList);
        }






        if (chestArrayList.size() != 0) {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                Intent intent = getIntent();
                Chest chest = intent.getSerializableExtra("outputChest", Chest.class);
                int position = intent.getIntExtra("Position", -1);
                if(position==-100){
                    upload.setEnabled(false);
                  chestArrayList =Chest.getChestList();
                  setRecyclerView(chestArrayList);
                  chestAdapter.notifyDataSetChanged();
                }else if(position !=-1){
                    upload.setEnabled(false);
                    //chestArrayList= Chest.getChestList();
                    //chestArrayList.add(chest);
                    chestArrayList.set(position, chest);
                    //Chest.setChestList(chestArrayList);
                    setRecyclerView(chestArrayList);
                    chestAdapter.notifyDataSetChanged();
                }


            } else {
                    Intent intent = getIntent();
                    Chest chest = (Chest) intent.getSerializableExtra("outputChest");
                    int position = intent.getIntExtra("Position", -1);
                if(position==-100){
                    upload.setEnabled(false);
                    chestArrayList =Chest.getChestList();
                    setRecyclerView(chestArrayList);
                    chestAdapter.notifyDataSetChanged();
                }else if(position !=-1){
                    upload.setEnabled(false);
                    //chestArrayList= Chest.getChestList();
                    //chestArrayList.add(chest);
                    chestArrayList.set(position, chest);
                    //Chest.setChestList(chestArrayList);
                    setRecyclerView(chestArrayList);
                    chestAdapter.notifyDataSetChanged();
                }
            }
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Sandık Ekle");


            builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String chestNum = chestNo.getText().toString();
                    Chest chest = new Chest(0, 0, chestNum);

                    Chest.addChestToList(chest);
                    chestArrayList = Chest.getChestList();
                    chestAdapter.notifyDataSetChanged();

                   /* if (chestArrayList.size() == 1) {
                        setRecyclerView(chestArrayList);
                    } else {
                        chestAdapter.notifyItemInserted(chestArrayList.size() - 1);

                    }*/

                    dialog.dismiss();

                }
            });

            builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });




            floatingActionButton = findViewById(R.id.addChest);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    chestNo = new EditText(MainActivity.this);
                    chestNo.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder.setView(chestNo);

                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            });


        }



        public void setRecyclerView (ArrayList < Chest > chestArrayList) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            chestAdapter = new ChestAdapter(chestArrayList);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(chestAdapter);


        }

        public void saveState (View view){
            ArrayList<Object> chestList = new ArrayList<>(chestArrayList);
            tinyDB.putListObject("chestList", chestList);
            Toast.makeText(MainActivity.this, "Kaydedildi", Toast.LENGTH_LONG).show();
        }
        public void upload(View view){
        ArrayList<Object> chestList = tinyDB.getListObject("chestList", Chest.class);
        ArrayList<Chest> chestArrayList1 = new ArrayList<>();
        if (chestList != null) {
            for (Object chest : chestList) {
                chestArrayList1.add((Chest) chest);
            }
            chestArrayList = chestArrayList1;
            Chest.setChestList(chestArrayList);
            setRecyclerView(chestArrayList);
            upload.setEnabled(false);
        }
    }


}