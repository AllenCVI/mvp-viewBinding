package com.newblink.blink.android.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.newblink.blink.android.databinding.ActivityVisitorBinding;
import com.newblink.blink.android.mine.adapter.VisitorRVEasyAdapter;
import com.newblink.blink.android.mine.realizationmvp.VisitorContract;
import com.newblink.blink.android.model.remote.responses.VisitorInfoBean;
import com.newblink.blink.android.base.mvpbase.BaseMvpActivity;

import java.util.ArrayList;
import java.util.List;

public class VisitorActivity extends BaseMvpActivity<ActivityVisitorBinding, VisitorContract.Presenter> implements VisitorContract.View{
   private RecyclerView rv_visitor;


    public static void start(Context context) {
        Intent intent = new Intent(context, VisitorActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initRVVisitor();

    }

    private void initRVVisitor() {
        rv_visitor = vBinding.rvVisitor;
        rv_visitor.setLayoutManager(new LinearLayoutManager(this));
        VisitorRVEasyAdapter visitorRVAdapter = new VisitorRVEasyAdapter(this);
        rv_visitor.setAdapter(visitorRVAdapter);
        List<VisitorInfoBean>  visitorInfoBeanList = new ArrayList<>();
        for (int i=0;i<9;i++){
            visitorInfoBeanList.add(new VisitorInfoBean());
        }
        visitorRVAdapter.setInfos(visitorInfoBeanList);

    }
}
