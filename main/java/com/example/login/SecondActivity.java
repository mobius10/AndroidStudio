package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    //UI Object
    private TextView txt_topbar;
    private TextView txt_course;
    private TextView txt_exercises;
    private TextView txt_my;
    private FrameLayout ly_content;

    //Fragment Object
    private SimpleFragment fg1, fg2, fg3;
    private FragmentManager fManager;

    private String[] names = {"第1章","第2章","第3章"};
    private int[] icons = {R.drawable.exercises_bg_1,R.drawable.exercises_bg_2,R.drawable.exercises_bg_3};
    private String[] introduces = {"共计5题","共计5题","共计5题"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        HomeAdapter homeAdapter = new HomeAdapter();
        recyclerView.setAdapter(homeAdapter);

        fManager = getFragmentManager();
        bindViews();
        txt_exercises.performClick();   //模拟一次点击，既进去后选择第一项
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{


        @androidx.annotation.NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(SecondActivity.this).inflate(R.layout.recycler_item,parent,false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@androidx.annotation.NonNull MyViewHolder holder, int position) {
            holder.name.setText(names[position]);
            holder.introduce.setText(introduces[position]);
            holder.iv.setImageResource(icons[position]);
        }

        @Override
        public int getItemCount() {
            return names.length;
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView name;
            TextView introduce;
            ImageView iv;

            public MyViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.name);
                introduce = itemView.findViewById(R.id.introduce);
                iv = itemView.findViewById(R.id.iv);
            }
        }
    }

    //UI组件初始化与事件绑定
    private void bindViews() {
        txt_topbar = (TextView) findViewById(R.id.txt_topbar);
        txt_course = (TextView) findViewById(R.id.txt_course);
        txt_exercises = (TextView) findViewById(R.id.txt_exercises);
        txt_my = (TextView) findViewById(R.id.txt_my);
        ly_content = (FrameLayout) findViewById(R.id.ly_content);

        txt_course.setOnClickListener(this);
        txt_exercises.setOnClickListener(this);
        txt_my.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    private void setSelected() {
        txt_course.setSelected(false);
        txt_exercises.setSelected(false);
        txt_my.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fg1 != null) fragmentTransaction.hide(fg1);
        if (fg2 != null) fragmentTransaction.hide(fg2);
        if (fg3 != null) fragmentTransaction.hide(fg3);
    }


    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()) {
            case R.id.txt_course:
                setSelected();
                txt_course.setSelected(true);
                if (fg1 == null) {
                    fg1 = new SimpleFragment();
                    String content = "第一个Fragment";
                    Bundle bd = new Bundle();
                    bd.putString("content", content);
                    fg1.setArguments(bd);
                    fTransaction.add(R.id.ly_content, fg1);
                } else {
                    fTransaction.show(fg1);
                }
                break;
            case R.id.txt_exercises:
                setSelected();
                txt_exercises.setSelected(true);
                if (fg2 == null) {
                    fg2 = new SimpleFragment();
                    String content = "第二个Fragment";
                    Bundle bd = new Bundle();
                    bd.putString("content", content);
                    fg2.setArguments(bd);
                    fTransaction.add(R.id.ly_content, fg2);
                } else {
                    fTransaction.show(fg2);
                }
                break;
            case R.id.txt_my:
                setSelected();
                txt_my.setSelected(true);
                if (fg3 == null) {
                    fg3 = new SimpleFragment();
                    String content = "第三个Fragment";
                    Bundle bd = new Bundle();
                    bd.putString("content", content);
                    fg3.setArguments(bd);
                    fTransaction.add(R.id.ly_content, fg3);
                } else {
                    fTransaction.show(fg3);
                }
                break;
        }
        fTransaction.commit();
    }
}