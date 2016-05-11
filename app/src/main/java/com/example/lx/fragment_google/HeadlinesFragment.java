package com.example.lx.fragment_google;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by LX on 2016/5/9.
 */
public class HeadlinesFragment extends ListFragment {

    OnHeadlineSelectedListener mCallback;

    //fragment和fragment之间的交互必须通过activity中转
    //fragment和activity之间的交互可通过在fragment中定义接口，在activity中实现该接口
    public interface OnHeadlineSelectedListener{
        public void onArticleSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, Ipsum.Headlines));
    }

    @Override
    public void onStart(){
        super.onStart();
        if (getFragmentManager().findFragmentById(R.id.article_fragment) != null){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }


    //fragment在生命周期中的onAttach()方法中获取接口的实现，通过调用接口的方法与activity交互，见onListItemClick
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            mCallback = (OnHeadlineSelectedListener)activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        mCallback.onArticleSelected(position);
        getListView().setItemChecked(position, true);
    }
}
