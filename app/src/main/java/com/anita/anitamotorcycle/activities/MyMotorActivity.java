package com.anita.anitamotorcycle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anita.anitamotorcycle.R;
import com.anita.anitamotorcycle.adapters.MyMotorDataAdapter;
import com.anita.anitamotorcycle.adapters.RecordDataAdapter;
import com.anita.anitamotorcycle.beans.MotorItemBean;
import com.anita.anitamotorcycle.beans.RecordItemBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MyMotorActivity extends BaseActivity {

    private RecyclerView mMyMototList;
    private List<MotorItemBean> mDatas;
    private ImageView mBack;
    private TextView mAddMotor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_motor);
        initView();
        initListener();
        //获取数据
        getData();
        showList(); //实现list
    }

    private void initListener() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    //后退操作
            }
        });

        mAddMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddMotorActivity.class);
                startActivity(intent);
            }
        });
    }

    /*
    TODO：从网络中获取数据，暂时模拟数据
     */
    private void getData() {
//        创建数据集合
        mDatas = new ArrayList<>();
//        创建模拟数据
        for (int i = 1; i <= 2; i++) {
//            创建数据对象
            MotorItemBean data = new MotorItemBean();
            data.plateNumbers = "车牌号" + i;
            data.model = "车辆型号" + i;
            data.factory = "制造商" + i;
            data.warrantyTime = 365 - i;
            data.warrantyDistance = 10000 - i;
//            data.url = "https://www.honda-sundiro.com/UpImage/Relate/20191104170922.jpg";
//            添加到集合里
            mDatas.add(data);
        }
    }

    private void initView() {
        mBack = findViewById(R.id.iv_back);
        mMyMototList = findViewById(R.id.rv_my_motor);
        mAddMotor = findViewById(R.id.tv_add_motor);
    }

    private void showList() {
//        设置recyclerview样式，设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mMyMototList.setLayoutManager(layoutManager);
//        mMyMototList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mMyMototList.setNestedScrollingEnabled(false);

//        创建适配器
        MyMotorDataAdapter adapter = new MyMotorDataAdapter(mDatas);
//        设置adaptor到recyclerview里
        mMyMototList.setAdapter(adapter);
    }
}
