package myapplication.app1.gamezone;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class GhostScreen extends AppCompatActivity {

    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private GhostDictionary dictionary;
    private boolean userTurn = false;
    private int flag = 0;
    private int score = 0;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_screen);

        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            dictionary = new SimpleDictionary(inputStream);
        } catch (IOException e) {
            Toast toast;
            toast = Toast.makeText(this ,"Dictionary not Loaded",Toast.LENGTH_SHORT);
            toast.show();
        }
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        onStart(null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onStart(View view) {
        flag=0;
        int n = random.nextInt(2);
        Button chall = (Button)findViewById(R.id.challenge);
        chall.setEnabled(true);
        TextView text = (TextView) findViewById(R.id.ghostText);
        text.setText("");
        TextView label = (TextView) findViewById(R.id.gameStatus);
        if (n == 0) {
            label.setText(USER_TURN);
        } else {
            label.setText(COMPUTER_TURN);
            computerTurn();
        }
        return true;
    }

    private void computerTurn() {
        TextView label = (TextView) findViewById(R.id.gameStatus);
        TextView ghostTv = (TextView)findViewById(R.id.ghostText);
        Button chall = (Button)findViewById(R.id.challenge);
        Button res = (Button)findViewById(R.id.restart);

        String word = ghostTv.getText().toString();

        if ((dictionary.isWord(word)) && (word.length() > 2))
        {
            label.setText("Word Found , you Won!");
            chall.setEnabled(false);
            score++;
            Toast.makeText(this, "Your Score : "+score, Toast.LENGTH_SHORT).show();
            res.setText("  Next Word  ");
            flag=1;
        }
        else
        {
            String longerWord = dictionary.getAnyWordStartingWith(word);
            if (longerWord != null)
            {
                char nextChar = longerWord.charAt(word.length());
                word += nextChar;
                ghostTv.setText(word);
                if ((dictionary.isWord(word)) && (word.length() > 4))
                {
                    label.setText("Computer Completed the Word , You Lose!");
                    score=0;
                    Toast.makeText(this, "Your Score : "+score, Toast.LENGTH_SHORT).show();
                    chall.setEnabled(false);
                    res.setText("  New Game  ");
                    flag=1;

                }
                else {
                    label.setText("User Turn");
                }
            }
            else
            {
                label.setText("you can't bluff now , You Lose!");
                chall.setEnabled(false);
                score=0;
                Toast.makeText(this, "Your Score : "+score, Toast.LENGTH_SHORT).show();
                res.setText("  New Game  ");
                flag=2;
            }
        }
        // Do computer turn stuff then make it the user's turn again
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (flag==1)
        {
            Toast.makeText(this, "Word Completed , Click On Restart", Toast.LENGTH_SHORT).show();
            return super.onKeyUp(keyCode, event);
        }
        if (flag==2)
        {
            Toast.makeText(this, "you can't bluff more, Click On Restart", Toast.LENGTH_SHORT).show();
            return super.onKeyUp(keyCode, event);
        }
        else
        {
            TextView ghostTv = (TextView) findViewById(R.id.ghostText);
            char keyPressed = (char) event.getUnicodeChar();
            if (Character.isLetter(keyPressed)) {
                String existingWord = ghostTv.getText().toString();
                existingWord += keyPressed;
                ghostTv.setText(existingWord);
                computerTurn();
                return true;
            }

            else {
                return super.onKeyUp(keyCode, event);
            }
        }
    }

    public void challenge(View view)
    {
        TextView label = (TextView)findViewById(R.id.gameStatus);
        TextView ghostTv = (TextView)findViewById(R.id.ghostText);
        Button res = (Button)findViewById(R.id.restart);

        String currentWord = ghostTv.getText().toString();

        if ((currentWord.length() > 3) && (dictionary.isWord(currentWord)))
        {
            label.setText("Word Found , Congratulations You Won the Challenge!");
            res.setText("  Next Word  ");
            flag=1;
        }
        else
        {
            String longerWord = dictionary.getAnyWordStartingWith(currentWord);
            if (longerWord != null)
            {
                label.setText("Computer won the Challenge , You Lose!");
                score=0;
                Toast.makeText(this, "Your Score : "+score, Toast.LENGTH_SHORT).show();
                res.setText("  New Game  ");
                ghostTv.setText(longerWord);
                Button chall = (Button)findViewById(R.id.challenge);
                chall.setEnabled(false);
                flag=1;
            }
            else
            {
                label.setText("No word Found, You Won the Challenge!");
                res.setText("  Next Word  ");
                flag=1;
            }
        }
    }

    public void restart (View view)
    {
        flag=0;
        Button res = (Button)findViewById(R.id.restart);
        res.setText("  New Game");
        TextView label = (TextView)findViewById(R.id.gameStatus);
        TextView word = (TextView)findViewById((R.id.ghostText));
        word.setText("");
        onStart(null);
    }

}
