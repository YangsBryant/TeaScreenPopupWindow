package com.example.popupwindowlibrary.view.adapter;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.popupwindowlibrary.R;
import com.example.popupwindowlibrary.bean.FiltrateBean;
import com.example.popupwindowlibrary.view.SkuFlowLayout;
import java.util.List;

public class ScreenListViewAdapter extends BaseAdapter {

    private Activity context;
    private List<FiltrateBean> dictList;

    private int radius = 12;
    private int strokeWidth = 2;
    private int strokeColor;
    private int boxWidth = 200;
    private int boxHeight = LinearLayout.LayoutParams.WRAP_CONTENT;
    private String checked = "#0aa666";
    private String enabled = "#000000";
    private int boxSize = 13;
    private int titleColor;
    private int titleSize = 14;
    private boolean isSingle = true;
    public ScreenListViewAdapter(Activity context, List<FiltrateBean> dictList) {
        this.context = context;
        this.dictList = dictList;
        strokeColor = context.getResources().getColor(R.color.green_66);
        titleColor = context.getResources().getColor(R.color.black);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    public void setBoxWidth(int boxWidth) {
        this.boxWidth = boxWidth;
    }

    public void setBoxHeight(int boxHeight) {
        this.boxHeight = boxHeight;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public void setBoxSize(int boxSize) {
        this.boxSize = boxSize;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public void setTitleSize(int titleSize) {
        this.titleSize = titleSize;
    }

    public void setSingle(boolean single) {
        isSingle = single;
    }

    @Override
    public int getCount() {
        return dictList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_property, null);
            vh = new ViewHolder();
            vh.tvTypeName = convertView.findViewById(R.id.tv_type_name);
            vh.layoutProperty = convertView.findViewById(R.id.layout_property);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        FiltrateBean filtrateBean = dictList.get(position);
        vh.tvTypeName.setText(filtrateBean.getTypeName());
        vh.tvTypeName.setTextColor(titleColor);
        vh.tvTypeName.setTextSize(titleSize);
        setFlowLayoutData(filtrateBean.getChildren(), vh.layoutProperty);

        return convertView;
    }

    private void setFlowLayoutData(final List<FiltrateBean.Children> childrenList, final SkuFlowLayout flowLayout) {

        flowLayout.removeAllViews();
        for (int x = 0; x < childrenList.size(); x++) {
            CheckBox checkBox = (CheckBox) View.inflate(context, R.layout.item_flowlayout_bill, null);
            GradientDrawable drawable = new GradientDrawable();
            //设置圆角大小
            drawable.setCornerRadius(radius);
            //设置shape背景色
            drawable.setStroke(strokeWidth,strokeColor);
            //设置宽高
            drawable.setSize(boxWidth,boxHeight);
            checkBox.setBackground(drawable);

            checkBox.setText(childrenList.get(x).getValue());
            checkBox.setTextSize(boxSize);
            checkBox.setTextColor(createColorStateList(checked,enabled));
            if (childrenList.get(x).isSelected()) {
                checkBox.setChecked(true);
                childrenList.get(x).setSelected(true);
            } else {
                checkBox.setChecked(false);
                childrenList.get(x).setSelected(false);
            }

            final int finalX = x;
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshCheckBox(flowLayout, finalX, childrenList);
                }
            });
            flowLayout.addView(checkBox);
        }
    }

    private static ColorStateList createColorStateList(String checked, String enabled) {
        int[] colors = new int[] { Color.parseColor(checked), Color.parseColor(enabled)};
        int[][] states = new int[2][];
        states[0] = new int[] { android.R.attr.state_checked};
        states[1] = new int[] { android.R.attr.state_enabled};
        return new ColorStateList(states, colors);
    }

    private void refreshCheckBox(SkuFlowLayout flowLayout, int finalX, List<FiltrateBean.Children> propBeenList) {
        for (int y = 0; y < flowLayout.getChildCount(); y++) {
            CheckBox radio = (CheckBox) flowLayout.getChildAt(y);
            if(isSingle) {
                radio.setChecked(false);
                propBeenList.get(y).setSelected(false);
            }
            if (finalX == y) {
                if(propBeenList.get(y).isSelected()) {
                    radio.setChecked(false);
                    propBeenList.get(y).setSelected(false);
                }else{
                    radio.setChecked(true);
                    propBeenList.get(y).setSelected(true);
                }
            }
        }
    }

    class ViewHolder {
        private TextView tvTypeName;
        private SkuFlowLayout layoutProperty;
    }
}
