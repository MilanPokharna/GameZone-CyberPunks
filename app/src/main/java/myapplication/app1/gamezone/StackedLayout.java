package myapplication.app1.gamezone;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Stack;

/**
 * Created by MiLaN on 05-11-2017.
 */

public class StackedLayout extends LinearLayout {

    private Stack<View> tiles = new Stack();

    public StackedLayout(Context context) {
        super(context);
    }

    public void push(View tile)
    {
        if (!empty())
        {
            removeView(tiles.peek());
        }
        tiles.add(tile);
        addView(tile);
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
    }

    public View pop() {
        View popped = null;
        if (!empty())
        {
            popped = tiles.pop();
            removeView(popped);
        }
        if (!empty())
            addView(peek());
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        return popped;
    }

    public View peek() {
        return tiles.peek();
    }

    public boolean empty() {
        return tiles.empty();
    }

    public void clear() {
        if (!empty())
        {
            removeAllViews();
            tiles.clear();
        }
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
    }
}
