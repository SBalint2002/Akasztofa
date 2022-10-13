package hu.petrik.akasztofa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button minusz;
    private Button plusz;
    private Button tipp;
    private TextView betu;
    private TextView szoveg;
    private ImageView kep;
   /* private ArrayList<String> szavak = new ArrayList<String>();
    private String gondolt;
    private Random rnd = new Random();
    private AlertDialog.Builder jatekVegeDialogBuilder;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        betu.setText("A");
        minusz = findViewById(R.id.minusz);
        plusz = findViewById(R.id.plusz);
        tipp = findViewById(R.id.tipp);
        betu = findViewById(R.id.betu);
        szoveg = findViewById(R.id.szoveg);
        kep = findViewById(R.id.kep);
    }
}