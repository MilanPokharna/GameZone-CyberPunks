package myapplication.app1.gamezone;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Mplayeasy extends AppCompatActivity {

    Button back;
    Intent intent;
    int User1TurnScore = 0;
    int User1OverallScore = 0;
    int User2TurnScore = 0;
    int User2OverallScore = 0;
    int flag;
    Button Broll;
    Button Bhold;
    Button Breset;
    TextView tv3;
    TextView tv1,tv2;
    ImageView iv;
    int Unum1;
    int Unum2;
    Context context;
    Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mplayeasy);
        back = (Button)  findViewById(R.id.Back4);
        Broll = (Button) findViewById(R.id.Roll);
        Bhold = (Button) findViewById(R.id.Hold);
        Breset = (Button) findViewById(R.id.Reset);
        iv = (ImageView) findViewById(R.id.image);
        tv1 = (TextView) findViewById(R.id.MtextView1);
        tv2 = (TextView) findViewById(R.id.MtextView2);
        tv3 = (TextView) findViewById(R.id.MtextView3);

        //On clicking back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Mplayeasy.this,Screen1D.class);
                startActivity(intent);
            }
        });

        Breset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User1TurnScore = 0;
                User1OverallScore = 0;
                User2TurnScore = 0;
                User2OverallScore = 0;
                tv1.setText("Player1 Score : " + User1OverallScore + "  Player2 Score : " + User2OverallScore);
                tv2.setText("Player1 Turn Score : " + User1TurnScore);
                tv3.setText("Player2 Turn Score : "+User2TurnScore);
                Bhold.setEnabled(true);  //disable the button "HOLD"
                Broll.setEnabled(true);  //disable the button "ROLL"
                context = getApplicationContext();
                toast = Toast.makeText(context,"Player1",Toast. LENGTH_SHORT);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                },100);


            }
        });
        User1Turn();




    }

    public void User1Turn()
    {

        context = getApplicationContext();
        toast = Toast.makeText(context, "Player 1", Toast.LENGTH_SHORT );
        toast.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        },100);
        Broll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation anim1 = AnimationUtils.loadAnimation(Mplayeasy.this, R.anim.diceanimation);
                final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Unum1 = RandomNum();
                        int NumOnRoll = Unum1;
                        if (animation == anim1) {
                            if (NumOnRoll == 1) {
                                iv.setImageResource(R.drawable.dice1);
                            } else if (NumOnRoll == 2) {
                                iv.setImageResource(R.drawable.dice2);
                            } else if (NumOnRoll == 3) {
                                iv.setImageResource(R.drawable.dice3);
                            } else if (NumOnRoll == 4) {
                                iv.setImageResource(R.drawable.dice4);
                            } else if (NumOnRoll == 5) {
                                iv.setImageResource(R.drawable.dice5);
                            } else {
                                iv.setImageResource(R.drawable.dice6);
                            }

                            if (Unum1 != 1) {
                                User1TurnScore += Unum1;

                                //changing text in text view
                                tv1.setText("Player1 Score : " + User1OverallScore + "  Player2 Score : " + User2OverallScore);
                                tv2.setText("Player1 Turn Score : " + User1TurnScore);
                                tv3.setText("Player2 Turn Score : " + User2TurnScore);

                            } else {
                                User1TurnScore = 0;
                                tv1.setText("Player1 Score : " + User1OverallScore + "  Player2 Score : " + User2OverallScore);
                                tv2.setText("Player1 Turn Score : " + User1TurnScore);
                                tv3.setText("Player2 Turn Score : " + User2TurnScore);
                                context = getApplicationContext();
                                toast = Toast.makeText(context, "Player 2", Toast.LENGTH_SHORT);
                                toast.show();
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        toast.cancel();
                                    }
                                }, 100);
                                User2Turn();
                            }
                        }}
                        @Override
                        public void onAnimationRepeat (Animation animation){


                        }


                    };
                    anim1.setAnimationListener(animationListener);
                iv.startAnimation(anim1);
                }});




        Bhold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User1OverallScore += User1TurnScore;
                User1TurnScore = 0;
                tv1.setText("Player1 Score : " + User1OverallScore + "  Player2 Score : " + User2OverallScore);
                tv2.setText("Player1 Turn Score : " + User1TurnScore);
                tv3.setText("Player2 Turn Score : "+User2TurnScore);
                if (User1OverallScore >= 100) {
                    flag=2;
                    WinActivity(flag);

                } else {
                    context = getApplicationContext();
                    toast = Toast.makeText(context, "Player2", Toast. LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    },100);



                    User2Turn();
                }  //calling functon to implement computer's turn
            }
        });


    }

    public void User2Turn()
    {


        Broll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Unum2 = RandomNum();
                if (Unum2 != 1) {
                    User2TurnScore += Unum2;

                    //changing text in text view
                    tv1.setText("Player1 Score : " + User1OverallScore + "  Player2 Score : " + User2OverallScore);
                    tv2.setText("Player1 Turn Score : " + User1TurnScore);
                    tv3.setText("Player2 Turn Score : "+User2TurnScore);

                } else {
                    User2TurnScore = 0;
                    tv1.setText("Player1 Score : " + User1OverallScore + "  Player2 Score : " + User2OverallScore);
                    tv2.setText("Player1 Turn Score : " + User1TurnScore);
                    tv3.setText("Player2 Turn Score : "+User2TurnScore);
                    /*Bhold.setEnabled(false);  //disable the button "HOLD"
                    Broll.setEnabled(false);*/
                    /*context = getApplicationContext();
                    toast = Toast.makeText(context, "Player 1", Toast. LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    },100);*/
                    User1Turn();
                }


            }
        });

        Bhold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User2OverallScore += User2TurnScore;
                User2TurnScore = 0;
                tv1.setText("Player1 Score : " + User1OverallScore + "  Player2 Score : " + User2OverallScore);
                tv2.setText("Player1 Turn Score : " + User1TurnScore);
                tv3.setText("Player2 Turn Score : "+User2TurnScore);
                if (User2OverallScore >= 100) {

                    /*Bhold.setEnabled(false);  //disable the button "HOLD"
                    Broll.setEnabled(false);  //disable the button "ROLL"*/
                    flag=1;
                    WinActivity(flag);

                } else {
                    /*Bhold.setEnabled(false);  //disable the button "HOLD"
                    Broll.setEnabled(false);*/
                    /*context = getApplicationContext();
                    toast = Toast.makeText(context, "Player1", Toast. LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    },100);*/



                    User1Turn();
                }  //calling functon to implement computer's turn
            }
        });


    }


    public int RandomNum()
    {
        int NumOnRoll = 1 + (int) (Math.random() * 5);

        return NumOnRoll;

    }

    public void WinActivity(int value)
    {
        intent = new Intent(Mplayeasy.this,Splayeasysc1.class);
        if (value==1)
        {

            intent.putExtra("Score","MEU");
            startActivity(intent);
        }
        else if(value==2)
        {
            intent.putExtra("Score","MEC");
            startActivity(intent);

        }
    }
}


