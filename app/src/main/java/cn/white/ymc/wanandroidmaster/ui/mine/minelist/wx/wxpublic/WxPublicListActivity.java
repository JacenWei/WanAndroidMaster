package cn.white.ymc.wanandroidmaster.ui.mine.minelist.wx.wxpublic;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.white.ymc.wanandroidmaster.R;
import cn.white.ymc.wanandroidmaster.base.BaseResultActivity;
import cn.white.ymc.wanandroidmaster.data.bean.WxPublicListBean;
import cn.white.ymc.wanandroidmaster.util.ConstantUtil;

/**
 * 微信公众号文章列表
 * <p>
 * 2018年10月24日 16:32:06
 *
 * @author ymc
 */
public class WxPublicListActivity extends BaseResultActivity implements WxPublicContract.View {

    @BindView(R.id.toolbar_wx_public)
    Toolbar toolbarWxPublic;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.normal_view)
    SmartRefreshLayout normalView;

    private WxPublicContract.Presenter presenter;
    private List<WxPublicListBean.DatasBean> datasBeanList;
    private int page = 1;
    private int publicId;
    private String title;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wx_public_list;
    }

    @Override
    protected void initView() {
        super.initView();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            title = bundle.getString(ConstantUtil.HOME_DETAIL_TITLE);
            getSupportActionBar().setTitle(title);
            publicId = bundle.getInt(ConstantUtil.HOME_DETAIL_ID, ConstantUtil.REQUEST_ERROR);
        }
        setSupportActionBar(toolbarWxPublic);
        toolbarWxPublic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        showLoading();
        datasBeanList = new ArrayList<>();
        presenter = new WxPublicPresenter(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        presenter.getWxPublicListResult(publicId, page);
    }

    @Override
    public void reload() {
        super.reload();

    }

    @Override
    public void getWxPublicListOk(WxPublicListBean bean, boolean hasRefresh) {

    }

    @Override
    public void getWxPublicErr(String err) {
        showError(err);
    }
}