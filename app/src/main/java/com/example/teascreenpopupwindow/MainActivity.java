package com.example.teascreenpopupwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popupwindowlibrary.bean.FiltrateBean;
import com.example.popupwindowlibrary.view.ScreenPopWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    private ScreenPopWindow flowPopWindow;
    private List<FiltrateBean> dictList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind( this );
        initParam();
        initView();
    }

    private void initView() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flowPopWindow = new ScreenPopWindow(MainActivity.this, dictList);
                flowPopWindow.build();
                flowPopWindow.showAsDropDown(button);
                flowPopWindow.setOnConfirmClickListener(new ScreenPopWindow.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick(List<String> list) {
                        StringBuilder str = new StringBuilder();
                        for (int i=0;i<list.size();i++) {
                            str.append(list.get(i)).append(" ");
                        }
                        Toast.makeText(MainActivity.this, str.toString(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }

    private void initParam() {
        String[] brand = {"花花公子", "语克","优衣库", "美特斯邦威", "森马", "翰代维", "PUMA"};
        String[] type = {"男装", "T恤", "运动服", "女装", "童装", "紧身衣"};


        FiltrateBean fb1 = new FiltrateBean();
        fb1.setTypeName("品牌");
        List<FiltrateBean.Children> childrenList = new ArrayList<>();
        for (String aBrand : brand) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(aBrand);
            childrenList.add(cd);
        }
        fb1.setChildren(childrenList);

        FiltrateBean fb2 = new FiltrateBean();
        fb2.setTypeName("类型");
        List<FiltrateBean.Children> childrenList2 = new ArrayList<>();
        for (String aType : type) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(aType);
            childrenList2.add(cd);
        }
        fb2.setChildren(childrenList2);

        dictList.add(fb1);
        dictList.add(fb2);
    }
}
