package hu.petrik.akasztofa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button minusz, plusz, tipp;
    private TextView betu, szoveg;
    private ImageView kep;
    private String gondolt;
    private Random r = new Random();
    private ArrayList betuk = new ArrayList();
    private ArrayList <String> hasznalt = new ArrayList<String>();
    private int index = 0;
    private int hibakSzama = 0;
    private AlertDialog.Builder gameOverDialog;
    private String[] szavak = {"Java", "Python", "Ruby", "JavaScript", "VisualBasic", "PHP", "Pascal","Kotlin","Haskell", "Groovy"};
    private int[] kepek = {R.drawable.akasztofa00,R.drawable.akasztofa01,R.drawable.akasztofa02,R.drawable.akasztofa03,R.drawable.akasztofa04,R.drawable.akasztofa05,R.drawable.akasztofa06, R.drawable.akasztofa07, R.drawable.akasztofa08, R.drawable.akasztofa09, R.drawable.akasztofa10, R.drawable.akasztofa11, R.drawable.akasztofa12, R.drawable.akasztofa13};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        plusz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                if (index > 25){
                    index = 0;
                    betu.setText(betuk.get(index).toString());
                }else {
                    betu.setText(betuk.get(index).toString());
                }
                if (hasznalt.contains(betu.getText().toString())){
                    betu.setTextColor(Color.BLACK);
                }else{
                    betu.setTextColor(Color.RED);
                }
            }
        });
        minusz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index--;
                if (index < 0){
                    index = 25;
                    betu.setText(betuk.get(index).toString());
                }else {
                    betu.setText(betuk.get(index).toString());
                }
                if (hasznalt.contains(betu.getText().toString())){
                    betu.setTextColor(Color.BLACK);
                }else{
                    betu.setTextColor(Color.RED);
                }
            }
        });
        tipp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tippeles();
            }
        });
    }

    private void tippeles(){
        hasznalt.add(betu.getText().toString());
        betu.setTextColor(Color.BLACK);
        StringBuilder sb = new StringBuilder(szoveg.getText());
        CharSequence tippBetu = betu.getText();
        if (gondolt.contains(tippBetu)){
            for (int i = 0; i < gondolt.length(); i++) {
                if (gondolt.charAt(i) == tippBetu.charAt(0)){
                    char ch = tippBetu.charAt(0);
                    sb.setCharAt(i, ch);
                }
            }
            szoveg.setText(sb);
        }else {
            hibakSzama++;
            if(hibakSzama < 13){
                kep.setImageResource(kepek[hibakSzama]);
            }else{
                kep.setImageResource(R.drawable.akasztofa13);
                gameOverDialog = new AlertDialog.Builder(this);
                gameOverDialog.setTitle("Nem sikerült kitalálni!");
                gameOverDialog.setMessage("Szeretnél még egyet játszani?");
                gameOverDialog.setCancelable(false);
                gameOverDialog.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                gameOverDialog.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        newGame();
                    }
                });
                gameOverDialog.create().show();
            }
        }
        if (szoveg.getText().toString().equals(gondolt)){
            gameOverDialog = new AlertDialog.Builder(this);
            gameOverDialog.setTitle("Helyes megfejtés!");
            gameOverDialog.setMessage("Szeretnél még egyet játszani?");
            gameOverDialog.setCancelable(false);
            gameOverDialog.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            gameOverDialog.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    newGame();
                }
            });
            gameOverDialog.create().show();
        }
    }

    private void newGame(){
        kep.setImageResource(R.drawable.akasztofa00);
        betu.setText("A");
        index = 0;
        hibakSzama = 0;
        betu.setTextColor(Color.RED);
        szoveg.setText("");
        feltolt();
        hasznalt.clear();
    }

    private void feltolt(){
        String szo = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        for(int i = 0; i < szo.length(); i++){
            betuk.add(szo.charAt(i));
        }
        gondolt = szavak[r.nextInt(szavak.length)].toUpperCase();
        for (int i = 0; i < gondolt.length(); i++) {
            szoveg.append("_");
        }
        Toast.makeText(this, gondolt, Toast.LENGTH_SHORT).show();
    }

    private void init(){
        minusz = findViewById(R.id.minusz);
        plusz = findViewById(R.id.plusz);
        tipp = findViewById(R.id.tipp);
        betu = findViewById(R.id.betu);
        betu.setTextColor(Color.RED);
        szoveg = findViewById(R.id.szoveg);
        kep = findViewById(R.id.kep);

        feltolt(); //A-Z + szavak listában, kiválasztott szöveg -> _ _ _
    }
}