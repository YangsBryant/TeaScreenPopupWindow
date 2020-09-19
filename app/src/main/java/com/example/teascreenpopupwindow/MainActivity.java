package com.example.teascreenpopupwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    private ScreenPopWindow screenPopWindow;
    private ScreenPopWindow screenPopWindow2;
    private ScreenPopWindow screenPopWindow3;

    private List<FiltrateBean> dictList = new ArrayList<>();
    private List<FiltrateBean> dictList2 = new ArrayList<>();
    private List<FiltrateBean> dictList3 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind( this );
        initParam();
        initView();
    }

    private void initView() {
        screenPopWindow = new ScreenPopWindow(MainActivity.this, dictList);
        screenPopWindow.build();


        screenPopWindow2 = new ScreenPopWindow(MainActivity.this, dictList2);
        //设置多选
        screenPopWindow2.setSingle(false).build();

        screenPopWindow3 = new ScreenPopWindow(MainActivity.this, dictList3);
        //设置单选-GNN模式
        screenPopWindow3.hideRadioButton(true)//开启单选GNN模式
                .setPopupTitle("小仙女服饰",getResources().getColor(R.color.black),16)//设置标题
                .hideTitle(false)//隐藏类别标题
                .build();
        screenPopWindow3.setOnRadioClickListener(new ScreenPopWindow.OnRadioClickListener() {
            @Override
            public void onRadioClick(String text) {
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screenPopWindow.showAsDropDown(button);
                screenPopWindow.setOnConfirmClickListener(new ScreenPopWindow.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick(List<String> list) {
                        StringBuilder str = new StringBuilder();
                        for (int i=0;i<list.size();i++) {
                            str.append(list.get(i)).append(" ");
                        }
                        Toast.makeText(MainActivity.this, str.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenPopWindow2.showAsDropDown(button2);
                screenPopWindow2.setOnConfirmClickListener(new ScreenPopWindow.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick(List<String> list) {
                        StringBuilder str = new StringBuilder();
                        for (int i=0;i<list.size();i++) {
                            str.append(list.get(i)).append(" ");
                        }
                        Toast.makeText(MainActivity.this, str.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenPopWindow3.showAsDropDown(button3);
            }
        });
    }

    private void initParam() {
        String[] brand = {"花花公子", "语克","优衣库", "美特斯邦威", "森马", "翰代维", "PUMA"};
        String[] type = {"男装", "T恤", "运动服", "女装", "童装", "紧身衣"};

        /*————防止数据错乱，不能共用javabean————*/
        FiltrateBean fb1 = new FiltrateBean();
        fb1.setTypeName("品牌");
        List<FiltrateBean.Children> childrenList = new ArrayList<>();
        for (String aBrand : brand) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(aBrand);
            childrenList.add(cd);
        }
        fb1.setChildren(childrenList);
        /*————————*/
        FiltrateBean fb2 = new FiltrateBean();
        fb2.setTypeName("类型");
        List<FiltrateBean.Children> childrenList2 = new ArrayList<>();
        for (String aType : type) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(aType);
            childrenList2.add(cd);
        }
        fb2.setChildren(childrenList2);
        /*————————*/
        FiltrateBean fb3 = new FiltrateBean();
        fb3.setTypeName("品牌");
        List<FiltrateBean.Children> childrenList3 = new ArrayList<>();
        for (String aBrand : brand) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(aBrand);
            childrenList3.add(cd);
        }
        fb3.setChildren(childrenList3);
        /*————————*/
        FiltrateBean fb4 = new FiltrateBean();
        fb4.setTypeName("类型");
        List<FiltrateBean.Children> childrenList4 = new ArrayList<>();
        for (String aType : type) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(aType);
            childrenList4.add(cd);
        }
        fb4.setChildren(childrenList4);
        /*————————*/
        FiltrateBean fb5 = new FiltrateBean();
        fb5.setTypeName("品牌");
        List<FiltrateBean.Children> childrenList5 = new ArrayList<>();
        for (String aBrand : brand) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(aBrand);
            childrenList5.add(cd);
        }
        fb5.setChildren(childrenList5);
        /*————————*/
        FiltrateBean fb6 = new FiltrateBean();
        fb6.setTypeName("类型");
        List<FiltrateBean.Children> childrenList6 = new ArrayList<>();
        for (String aType : type) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(aType);
            childrenList6.add(cd);
        }
        fb6.setChildren(childrenList6);

        dictList.add(fb1);
        dictList.add(fb2);

        dictList2.add(fb3);
        dictList2.add(fb4);

        dictList3.add(fb5);
    }
}
