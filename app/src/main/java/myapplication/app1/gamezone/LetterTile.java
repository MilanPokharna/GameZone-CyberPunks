package myapplication.app1.gamezone;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by MiLaN on 05-11-2017.
 */

public class LetterTile extends android.support.v7.widget.AppCompatTextView {

    public static final int TILE_SIZE = 150;
    private Character letter;
    private boolean frozen;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public LetterTile(Context context, Character letter) {
        super(context);
        this.letter = letter;
        setText(letter.toString());
        setTextAlignment(TEXT_ALIGNMENT_CENTER);
        setHeight(TILE_SIZE);
        setWidth(TILE_SIZE);
        setTextSize(30);
        setBackgroundColor(Color.rgb(255,0,0));
    }

    public void moveToViewGroup(ViewGroup targetView) {
        ViewParent parent = getParent();
        if (parent instanceof StackedLayout ) {
            StackedLayout owner = (StackedLayout) parent;
            owner.pop();
            targetView.addView(this);
            freeze();
            setVisibility(View.VISIBLE);
        } else {
            ViewGroup owner = (ViewGroup) parent;
            owner.removeView(this);
            ((StackedLayout) targetView).push(this);
            unfreeze();
        }
    }

    public void freeze() {
        frozen = true;
    }

    public void unfreeze() {
        frozen = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if ((!frozen)&&(motionEvent.getAction() == MotionEvent.ACTION_DOWN ))
        {

            return startDrag(ClipData.newPlainText("",""),new View.DragShadowBuilder(this),this,0);
        }



        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        else
        {
            return super.onTouchEvent(motionEvent);
        }

    }
}

