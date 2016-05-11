package com.example.lx.fragment_google;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements HeadlinesFragment.OnHeadlineSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null){
                return;
            }

            HeadlinesFragment firstFragment = new HeadlinesFragment();
            firstFragment.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();  //getFragmentManager for v7; getSupportFragmentManager for v4
        }
    }

    public void onArticleSelected(int position){

        ArticleFragment articleFragment = (ArticleFragment)getFragmentManager()
                .findFragmentById(R.id.article_fragment);

        if (articleFragment != null){
            articleFragment.updateArticleView(position);
        }else{
            ArticleFragment newFragment = new ArticleFragment();
            Bundle args = new Bundle();
            args.putInt(ArticleFragment.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
