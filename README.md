# TeaScreenPopupWindow
多类型筛选弹框

![这是一张图片](https://github.com/YangsBryant/TeaScreenPopupWindow/blob/master/asdafafaa.gif)

## 引入module
```java
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://www.jitpack.io' }
    }
}
```
```java
implementation 'com.github.YangsBryant:TeaScreenPopupWindow:1.0.2'
```

## 主要代码
```java
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
```
## TeaScreenPopupWindow属性大全
方法名 | 属性
--------- | -------------
setPopupTitle(String text,int textColor,int size) | 设置PopWindow的标题, 内容默认无 ,颜色默认#ffffff， 字体大小默认16
setTopView(Boolean bl, int color) | 设置顶部分割线是否显示，以及颜色。默认true,#f3f3f3
setBottomView(Boolean bl, int color) | 设置底部分割线是否显示，以及颜色。默认true,#f3f3f3
setConfirm(String text, int size, int textColor, int color) | 设置确定按钮的文字，字体大小，字体颜色，背景颜色。默认“确定”，14，#ffffff，#0aa666
setReset(String text, int size, int textColor, int color) | 设置重置按钮的文字，字体大小，字体颜色，背景颜色。默认“重置”，#000000，#ffffff
setAlpha(int mAlpha) | 设置阴影层的透明度 默认是0.5f
hideTitle(boolean bl)  | 设置分类title是否显示
setTitleColor(int color) | 设置title的字体颜色,默认#000000
setTitleSize(int size) | 设置title的字体大小,默认14
setRadius(int radius) | 设置item圆角大小，默认12
setStrokeWidth(int width) | 设置item边框粗细，默认2
setStrokeColor(int color) | 设置item边框颜色，默认#0aa666
setBoxWidth(int width) | 设置item宽度，默认是200dp
setBoxHeight(int height) | 设置item高度，默认是WRAP_CONTENT
setChecked(String color) | 设置item选中时的颜色，默认#0aa666
setEnabled(String color) | 设置item未选中时的颜色，默认#000000
setBoxSize(int size) | 设置item字体大小，默认13
setSingle(boolean bl) | 设置是否开启单选，默认单选
hideRadioButton(boolean bl) | 设置单选GNN模式
reset() | 显示控件时数据重置
build() | 参数设置完毕，一定要build一下

## 联系QQ：961606042
