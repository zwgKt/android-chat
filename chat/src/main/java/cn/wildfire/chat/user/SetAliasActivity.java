package cn.wildfire.chat.user;

import androidx.lifecycle.ViewModelProviders;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import cn.wildfirechat.chat.R;
import cn.wildfire.chat.third.utils.UIUtils;

import butterknife.Bind;
import butterknife.OnTextChanged;
import cn.wildfire.chat.WfcBaseActivity;

/**
 * @创建者 CSDN_LQR
 * @描述 设备备注界面
 */

public class SetAliasActivity extends WfcBaseActivity {

    private String mFriendId;
//    private Friend mFriend;

    @Bind(R.id.aliasEditText)
    EditText aliasEditText;

    private MenuItem menuItem;

    @Override
    protected int contentLayout() {
        return R.layout.contact_set_alias_activity;
    }

    @Override
    protected void afterViews() {
        mFriendId = getIntent().getStringExtra("userId");
        if (TextUtils.isEmpty(mFriendId)) {
            finish();
            return;
        }
    }

    @Override
    protected int menu() {
        return R.menu.user_set_alias;
    }

    @Override
    protected void afterMenus(Menu menu) {
        menuItem = menu.findItem(R.id.save);
        menuItem.setEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            changeAlias();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnTextChanged(R.id.aliasEditText)
    void onAliasEditTextChange() {
        menuItem.setEnabled(aliasEditText.getText().toString().trim().length() > 0 ? true : false);
    }

    private void changeAlias() {
        String displayName = aliasEditText.getText().toString().trim();
        if (TextUtils.isEmpty(displayName)) {
            UIUtils.showToast(UIUtils.getString(R.string.alias_no_empty));
            return;
        }
        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    private void changeError(Throwable throwable) {
        UIUtils.showToast(throwable.getLocalizedMessage());
    }

}
