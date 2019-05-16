package com.example.admin.data.FastScroll;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.admin.data.R;

import androidx.recyclerview.widget.RecyclerView;


public class FastScrollRecyclerViewItemDecoration extends RecyclerView.ItemDecoration{
    private Context mContext;
    public FastScrollRecyclerViewItemDecoration(Context context) {
        mContext = context;
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(canvas, parent, state);

        float scaledWidth = ((FastScrollRecyclerView)parent).scaledWidth;
        float sx = ((FastScrollRecyclerView)parent).sx;
        float scaledHeight= ((FastScrollRecyclerView)parent).scaledHeight;
        float sy = ((FastScrollRecyclerView)parent).sy;
        String[] sections = ((FastScrollRecyclerView)parent).sections;
        String section = ((FastScrollRecyclerView)parent).section;
        boolean showLetter = ((FastScrollRecyclerView)parent).showLetter;

        // We draw the letter in the middle
        if (showLetter & section != null && !section.equals("")) {
            //overlay everything when displaying selected index Letter in the middle
            Paint overlayDark = new Paint();
            overlayDark.setColor(Color.TRANSPARENT);
            //overlayDark.setAlpha(100);
            // canvas.drawRect(0, 0, parent.getWidth(), parent.getHeight(),null);
            canvas.drawRect(0, 0, parent.getWidth(), parent.getHeight(), overlayDark);
            // float middleTextSize =100;

            float middleTextSize = mContext.getResources().getDimension(R.dimen.fast_scroll_overlay_text_size);
            Paint middleLetter = new Paint();
            //middleLetter.setColor(Color.GRAY);
            //  middleLetter.setAlpha(100);
            middleLetter.setColor(Color.RED);
            middleLetter.setTextSize(middleTextSize);
            middleLetter.setAntiAlias(true);
            middleLetter.setFakeBoldText(true);
            middleLetter.setStyle(Paint.Style.FILL);
            int xPos = (canvas.getWidth() -  (int)middleTextSize)/ 2;
            int yPos = (int) ((canvas.getHeight() / 2) - ((middleLetter.descent() + middleLetter.ascent()) / 2));
                    canvas.drawText(section.toUpperCase(), xPos, yPos, middleLetter);


        }
        // draw indez A-Z

        Paint textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);

        for (int i = 0; i < sections.length; i++) {
            if(showLetter & section != null && !section.equals("") && section!=null
                    && sections[i].toUpperCase().equals(section.toUpperCase())) {
                textPaint.setColor(Color.RED);
                textPaint.setAlpha(250);
                textPaint.setFakeBoldText(true);
                textPaint.setTextSize((float)(scaledWidth / 2));
                canvas.drawText(sections[i].toUpperCase(), sx + textPaint.getTextSize() / 2, sy + parent.getPaddingTop() + scaledHeight * (i + 1), textPaint);
                textPaint.setTextSize((float)(scaledWidth));
                textPaint.setColor(Color.RED);
                        canvas.drawText("•",
                                sx - textPaint.getTextSize()/3, sy+parent.getPaddingTop()
                                        + scaledHeight * (i + 1) + scaledHeight/3, textPaint);


            } else
                {
                textPaint.setColor(Color.RED);
                textPaint.setAlpha(250);
                textPaint.setFakeBoldText(false);
                textPaint.setTextSize(scaledWidth / 2);
                canvas.drawText(sections[i].toUpperCase(), sx + textPaint.getTextSize() / 2, sy + parent.getPaddingTop() + scaledHeight * (i + 1), textPaint);



            }

        }




    }
}