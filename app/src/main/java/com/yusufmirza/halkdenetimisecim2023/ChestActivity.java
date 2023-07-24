package com.yusufmirza.halkdenetimisecim2023;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ChestActivity extends AppCompatActivity {

    ArrayList<Chest> chestArrayList;
    EditText editTextRecep,editTextKemal;
    String chestNumber;
    int position;
    Intent intent;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chest_activity);

        editTextKemal= findViewById(R.id.editTextKemal);
        editTextRecep= findViewById(R.id.editTextRecep);




         intent = getIntent();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            Chest chest = intent.getSerializableExtra("chest",Chest.class);
            position= intent.getIntExtra("position",-1);
            int recepoy = chest.tayyip;
            int kemaloy = chest.kemal;
            chestNumber = chest.chestNumber;
            editTextKemal.setText(Integer.toString(kemaloy));
            editTextRecep.setText(Integer.toString(recepoy));
        } else {
            Chest chest = (Chest) intent.getSerializableExtra("chest");
            position= intent.getIntExtra("position",-1);
            int recepoy = chest.tayyip;
            int kemaloy = chest.kemal;
            chestNumber = chest.chestNumber;
            editTextKemal.setText(Integer.toString(kemaloy));
            editTextRecep.setText(Integer.toString(recepoy));
        }


    }
    public void gobackChest(View view) {
        Intent intent1 = new Intent(ChestActivity.this, MainActivity.class);
        intent1.putExtra("Position",position);
        int kemalayaroy =  Integer.parseInt(editTextKemal.getText().toString());
        int recepoy= Integer.parseInt(editTextRecep.getText().toString());
        Chest outputChest = new Chest(recepoy,kemalayaroy,chestNumber);
        intent1.putExtra("outputChest",outputChest);
        startActivity(intent1);
    }
    public void editKemal(View view) {
        if(editTextKemal.isEnabled()){
            editTextKemal.setEnabled(false);
        } else {
            editTextKemal.setEnabled(true);
        }
    }
    public void editErdogan(View view) {
        if(editTextRecep.isEnabled()){
            editTextRecep.setEnabled(false);
        } else {
            editTextRecep.setEnabled(true);
        }
    }
    @SuppressLint("SetTextI18n")
    public void minusKK(View view){
        int KKvoted =Integer.parseInt(editTextKemal.getText().toString());
        KKvoted = KKvoted-1;
        editTextKemal.setText(Integer.toString(KKvoted));
    }
    @SuppressLint("SetTextI18n")
    public void minusRTE(View view){
        int RTEvoted =Integer.parseInt(editTextRecep.getText().toString());
        RTEvoted = RTEvoted-1;
        editTextRecep.setText(Integer.toString(RTEvoted));
    }
    @SuppressLint("SetTextI18n")
    public void plusRTE(View view) {
        int RTEvoted =Integer.parseInt(editTextRecep.getText().toString());
        RTEvoted = RTEvoted+1;
        editTextRecep.setText(Integer.toString(RTEvoted));
    }
    @SuppressLint("SetTextI18n")
    public void plusKK(View view) {
        int KKvoted =Integer.parseInt(editTextKemal.getText().toString());
        KKvoted = KKvoted+1;
        editTextKemal.setText(Integer.toString(KKvoted));
    }
    public void delete(View view) {
        Intent intent2 = new Intent(ChestActivity.this, MainActivity.class);
        intent2.putExtra("Position",-100); //Delete Kodu -100
        position = intent.getIntExtra("position",-1);

        chestArrayList=Chest.getChestList();
        ArrayList<Chest> chestArrayList1=Chest.deleteItem(chestArrayList,position);
        Chest.setChestList(chestArrayList1);
        startActivity(intent2);
    }
}
