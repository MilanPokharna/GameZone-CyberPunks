package myapplication.app1.gamezone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity {

    Button Bword;
    Button Bdice;
    Intent intent;

    Animation myAnimation;
    TextView myText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        myText = (TextView)findViewById(R.id.GameZone);

        myAnimation = AnimationUtils.loadAnimation(this, R.anim.myanimation);
        myText.startAnimation(myAnimation);

        Bword = (Button) findViewById(R.id.ButtonWord);
        Bdice = (Button) findViewById(R.id.ButtonDice);


        Bword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainScreen.this,Screen1.class);
                startActivity(intent);
            }
        });

        Bdice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainScreen.this,Screen1D.class);
                startActivity(intent);
            }
        });
    }
}
