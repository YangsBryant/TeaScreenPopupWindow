package com.example.popupwindowlibrary.view;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.example.popupwindowlibrary.R;
import com.example.popupwindowlibrary.bean.FiltrateBean;
import com.example.popupwindowlibrary.view.adapter.ScreenListViewAdapter;
import java.util.ArrayList;
import java.util.List;

public class ScreenPopWindow extends PopupWindow {

    private final Activity context;
    private final List<FiltrateBean> dictList;
    private CustomHeightListView mListView;
    private TextView tvReset, tvConfirm;
    private AppCompatTextView popupTitle;
    private View nullView,topView,bottomView;
    private ScreenListViewAdapter adapter;
    private OnConfirmClickListener onConfirmClickListener;
    private OnRadioClickListener onRadioClickListener;
    private int alpha=0x1A000000;
    public ScreenPopWindow(Activity context, List<FiltrateBean> dictList ) {
        this.context = context;
        this.dictList=dictList;
        initView();
    }

    //设置PopWindow的标题, 内容默认无 ,颜色默认#ffffff， 字体大小默认16
    public ScreenPopWindow setPopupTitle(String text,int textColor,int size){
        if(text!=null){
            popupTitle.setText(text);
            popupTitle.setVisibility(View.VISIBLE);
        }else{
            popupTitle.setVisibility(View.GONE);
        }
        popupTitle.setTextColor(textColor);
        popupTitle.setTextSize(size);
        return this;
    }

    //设置顶部分割线是否显示，以及颜色。默认true,#f3f3f3
    public ScreenPopWindow setTopView(Boolean bl, int color){
        if(bl) {
            topView.setVisibility(View.VISIBLE);
        }else{
            topView.setVisibility(View.GONE);
        }
        topView.setBackgroundColor(color);
        return this;
    }

    //设置底部分割线是否显示，以及颜色。默认true,#f3f3f3
    public ScreenPopWindow setBottomView(Boolean bl, int color){
        if(bl) {
            bottomView.setVisibility(View.VISIBLE);
        }else{
            bottomView.setVisibility(View.GONE);
        }
        bottomView.setBackgroundColor(color);
        return this;
    }

    //设置确定按钮的文字，字体大小，字体颜色，背景颜色。默认“确定”，14，#ffffff，#0aa666，
    public ScreenPopWindow setConfirm(String text, int size, int textColor, int color){
        tvConfirm.setText(text);
        tvConfirm.setTextSize(size);
        tvConfirm.setTextColor(textColor);
        tvConfirm.setBackgroundColor(color);
        return this;
    }

    //设置重置按钮的文字，字体大小，字体颜色，背景颜色。默认“重置”，#000000，#ffffff
    public ScreenPopWindow setReset(String text, int size, int textColor, int color){
        tvReset.setText(text);
        tvReset.setTextSize(size);
        tvReset.setTextColor(textColor);
        tvReset.setBackgroundColor(color);
        return this;
    }

    //设置阴影层的透明度 默认是0.5f
    public ScreenPopWindow setAlpha(int mAlpha){
        alpha = mAlpha;
        return this;
    }

    //设置分类title是否显示
    public ScreenPopWindow hideTitle(boolean bl){
        adapter.setHideTitle(bl);
        return this;
    }

    //设置title的字体颜色,默认#000000
    public ScreenPopWindow setTitleColor(int color){
        adapter.setTitleColor(color);
        return this;
    }

    //设置title的字体大小,默认14
    public ScreenPopWindow setTitleSize(int size){
        adapter.setTitleSize(size);
        return this;
    }

    //设置item圆角大小，默认12
    public ScreenPopWindow setRadius(int radius){
        adapter.setRadius(radius);
        return this;
    }

    //设置item边框粗细，默认2
    public ScreenPopWindow setStrokeWidth(int width){
        adapter.setStrokeWidth(width);
        return this;
    }

    //设置item边框颜色，默认#0aa666
    public ScreenPopWindow setStrokeColor(int color){
        adapter.setStrokeColor(color);
        return this;
    }

    //设置item宽度，默认是200dp
    public ScreenPopWindow setBoxWidth(int width){
        adapter.setBoxWidth(width);
        return this;
    }

    //设置item高度，默认是WRAP_CONTENT
    public ScreenPopWindow setBoxHeight(int height){
        adapter.setBoxHeight(height);
        return this;
    }

    //设置item选中时的颜色，默认#0aa666
    public ScreenPopWindow setChecked(String color){
        adapter.setChecked(color);
        return this;
    }

    //设置item未选中时的颜色，默认#000000
    public ScreenPopWindow setEnabled(String color){
        adapter.setEnabled(color);
        return this;
    }

    //设置item字体大小，默认13
    public ScreenPopWindow setBoxSize(int size){
        adapter.setBoxSize(size);
        return this;
    }

    //设置是否开启单选，默认单选
    public ScreenPopWindow setSingle(boolean bl){
        adapter.setSingle(bl);
        return this;
    }

    //设置单选GNN模式
    public ScreenPopWindow hideRadioButton(boolean bl){
        if(bl){
            tvConfirm.setVisibility(View.GONE);
            tvReset.setVisibility(View.GONE);
        }else{
            tvConfirm.setVisibility(View.VISIBLE);
            tvReset.setVisibility(View.VISIBLE);
        }
        adapter.setHideRadio(bl);
        return this;
    }

    //显示控件时数据重置
    public ScreenPopWindow reset(){
        for (int x = 0; x < dictList.size(); x++) {
            List<FiltrateBean.Children> childrenBeen = dictList.get(x).getChildren();
            for (int y=0;y<childrenBeen.size();y++){
                if (childrenBeen.get(y).isSelected())
                    childrenBeen.get(y).setSelected(false);
            }
        }
        return this;
    }

    //参数设置完毕，一定要build一下
    public void build(){
        initPop();
    }

    private void initView(){
        View popView = View.inflate(context, R.layout.flow_pop_listview, null);
        //设置view
        this.setContentView(popView);
        //设置宽高（也可设置为LinearLayout.LayoutParams.MATCH_PARENT或者LinearLayout.LayoutParams.MATCH_PARENT）
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow的焦点
        this.setFocusable(true);
        adapter = new ScreenListViewAdapter(context, dictList);
        mListView = popView.findViewById(R.id.listview);
        popupTitle = popView.findViewById(R.id.popupTitle);
        tvReset = popView.findViewById(R.id.tv_reset);
        tvConfirm = popView.findViewById(R.id.tv_confirm);
        nullView = popView.findViewById(R.id.view_null);
        topView = popView.findViewById(R.id.topView);
        bottomView = popView.findViewById(R.id.bottomView);
    }

    private void initPop() {
        //设置背景透明
        this.setBackgroundDrawable(new ColorDrawable(alpha));
        mListView.setAdapter(adapter);
        adapter.setArrowListener(new ScreenListViewAdapter.ArrowClickListener() {
            @Override
            public void onArrowClick(String content) {
                onRadioClickListener.onRadioClick(content);
                dismiss();
            }
        });
        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int x = 0; x < dictList.size(); x++) {
                    List<FiltrateBean.Children> childrenBeen = dictList.get(x).getChildren();
                    for (int y=0;y<childrenBeen.size();y++){
                        if (childrenBeen.get(y).isSelected())
                            childrenBeen.get(y).setSelected(false);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> list = new ArrayList<>();
                for (FiltrateBean fb : dictList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected()){
                            list.add(children.getValue());
                        }
                    }
                }
                onConfirmClickListener.onConfirmClick(list);
                dismiss();
            }
        });
        nullView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener){
        this.onConfirmClickListener=onConfirmClickListener;
    }

    public interface OnConfirmClickListener{
        void onConfirmClick(List<String> list);
    }

    public void setOnRadioClickListener(OnRadioClickListener onRadioClickListener){
        this.onRadioClickListener=onRadioClickListener;
    }

    public interface OnRadioClickListener{
        void onRadioClick(String text);
    }
}
