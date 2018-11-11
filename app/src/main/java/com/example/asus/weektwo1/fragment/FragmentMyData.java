package com.example.asus.weektwo1.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwie.juan_mao.xlistview.XListView;
import com.example.asus.weektwo1.MyDataAdapter;
import com.example.asus.weektwo1.R;
import com.example.asus.weektwo1.bean.MyDataBean;
import com.example.asus.weektwo1.presenter.FragmentPresenter;
import com.example.asus.weektwo1.presenter.Presenter;
import com.example.asus.weektwo1.view.IView;

import java.util.ArrayList;
import java.util.List;

public class FragmentMyData extends Fragment implements IView<MyDataBean> {

    private String url = "http://www.xieast.com/api/news/news.php";

    private FragmentPresenter myDataPresenter;
    private XListView mXList;
    private List<MyDataBean.DataBean> mList;
    private MyDataAdapter mMyDataAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_my_data,null);
        //1 初始化
        getinit(view);
        //2 设置XList
        setOnXListView(view);
        return view;
    }

    private void setOnXListView(View view) {
        mList = new ArrayList<>();
        //初始化一个适配器
        mMyDataAdapter = new MyDataAdapter(getActivity(), mList);
        //为list设置
        mXList.setAdapter(mMyDataAdapter);
    }

    private void getinit(View view) {
        mXList = view.findViewById(R.id.xlist);
        //设置可上拉下拉
        mXList.setPullRefreshEnable(true);
        mXList.setPullLoadEnable(true);

        //初始化p层对象
        myDataPresenter = new FragmentPresenter();
        myDataPresenter.attch(this);
        myDataPresenter.getMyDataPresenter(url);
    }

    @Override
    public void success(MyDataBean myDataBean) {
        if(myDataBean!=null){
            List<MyDataBean.DataBean> data = myDataBean.getData();
            if(data!=null){
                mList.clear();
                mList.addAll(data);
                mMyDataAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void failed(Object e) {
        Toast.makeText(getActivity(),""+e,Toast.LENGTH_SHORT).show();
    }
}
