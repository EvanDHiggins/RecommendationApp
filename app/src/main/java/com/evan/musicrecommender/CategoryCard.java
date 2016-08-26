package com.evan.musicrecommender;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by evanh on 8/18/2016.
 */
public class CategoryCard extends CardView {
    public CategoryCard(Context context) {
        super(context);
        init();
    }

    public CategoryCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.category_card, this);
        //CardView.LayoutParams layoutParams = (CardView.LayoutParams)getLayoutParams();
        int margin = (int)getResources().getDimension(R.dimen.category_card_margins);
        System.out.println("Margin = " + margin);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, margin, 0, 0);
        //layoutParams.setMargins(margin, margin, margin, margin);
        setLayoutParams(layoutParams);

        ViewGroup.LayoutParams blah = getLayoutParams();

    }
}
