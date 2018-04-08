package myapplication.app1.gamezone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class Screen1 extends AppCompatActivity {

    Animation myAnimation;

    Button Banagram;
    Button Bword;
    Button Bghost;
    Intent intent;
    Button Bback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);

        myAnimation = AnimationUtils.loadAnimation(this , R.anim.myanimation);

        Banagram = (Button) findViewById(R.id.Anagram);
        Bword = (Button) findViewById(R.id.Wstack);
        Bghost = (Button) findViewById(R.id.Ghost);
        Bback =(Button) findViewById(R.id.Back);

        Banagram.startAnimation(myAnimation);
        Bword.startAnimation(myAnimation);
        Bghost.startAnimation(myAnimation);

        //On clicking Anagram button
        Banagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Screen1.this,AnagramScreen.class);
                startActivity(intent);
            }
        });

        //On clicking WORD STACK button
        Bword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Screen1.this,WordScreen.class);
                startActivity(intent);
            }
        });
        //On clicking ghost word button
        Bghost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Screen1.this,GhostScreen.class);
                startActivity(intent);
            }
        });

        //On clicking back
        Bback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Screen1.this,MainScreen.class);
                startActivity(intent);
            }
        });

    }
}
