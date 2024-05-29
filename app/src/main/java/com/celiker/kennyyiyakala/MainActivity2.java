package com.celiker.kennyyiyakala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {



    TextView scoretext;
    TextView timetext;
    int score;
    ImageView kenny1;
    ImageView kenny2;
    ImageView kenny3;
    ImageView kenny4;
    ImageView kenny5;
    ImageView kenny6;
    ImageView kenny7;
    ImageView kenny8;
    ImageView kenny9;
//Dizi oluşturacağız.
    ImageView[] kennyarray;
//Runnable'ı kullanabilmemiz için gerekli olan bir sınıf : handler
    Handler handler;
    Runnable runnable;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //initialize, başlatmak
        timetext=findViewById(R.id.timetext);
        scoretext=findViewById(R.id.scoretext);
        kenny1=findViewById(R.id.kenny1);
        kenny2=findViewById(R.id.kenny2);
        kenny3=findViewById(R.id.kenny3);
        kenny4=findViewById(R.id.kenny4);
        kenny5=findViewById(R.id.kenny5);
        kenny6=findViewById(R.id.kenny6);
        kenny7=findViewById(R.id.kenny7);
        kenny8=findViewById(R.id.kenny8);
        kenny9=findViewById(R.id.kenny9);





        kennyarray=new ImageView[]{kenny1,kenny2,kenny3,kenny4,kenny5,kenny6,kenny7,kenny8,kenny9};

        hideImages();


        score=0;


        new CountDownTimer(21000,1000) {
            @Override
            public void onTick(long l) {
                timetext.setText("Time: "+ l/1000);
            }

            @Override
            public void onFinish() {
                timetext.setText("Time Off! ");
                handler.removeCallbacks(runnable);
                for (ImageView image : kennyarray) {
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity2.this);//countdown timer'ın
                // içinde yazıldığı için
                //sadece this diye yazamayız, MainActivity'i belirtmek zorundayız.
                alert.setTitle("Restart");
                alert.setMessage("Are you sure about to restart the game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //restart kodu:

                        Intent intent =getIntent();//eğer bize gönderilen intent yoksa;
                        finish();//uygulamayı yormamak için aktiviteyi tamamen kapatır.
                        startActivity(intent);

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity2.this, "Game Over!", Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();
            }
        }.start();
    }




    public void increaseScore (View view){
        score++;//veya score= score+1
scoretext.setText("Score: "+score);

    }


    public void hideImages(){
        handler= new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for ( ImageView image: kennyarray){
                    image.setVisibility(View.INVISIBLE);
            }
                Random random= new Random();
                    int x=random.nextInt(9);//9 tane elemandan rastgele birini seçer.
                    kennyarray[x].setVisibility(View.VISIBLE);//herhangi bir dizi elamanını göstermek
                //ve diğerlerini silmek için
                // köşeli parantez içine
                // x yazmak gerekir. eğer 0-8 arası bir sayı girersek
                // sadece onu gösterir oyun boyunca.
                handler.postDelayed(this, 500);

            }

        };
handler.post(runnable);
    }



    public void changeActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);//get yerine MainActivity2.this de denebilirdi.
        startActivity(intent);
    }

}




