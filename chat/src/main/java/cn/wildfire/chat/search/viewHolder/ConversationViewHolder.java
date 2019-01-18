package cn.wildfire.chat.search.viewHolder;

import androidx.lifecycle.ViewModelProviders;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import cn.wildfirechat.chat.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.wildfire.chat.group.GroupViewModel;
import cn.wildfire.chat.user.UserViewModel;
import cn.wildfirechat.model.Conversation;
import cn.wildfirechat.model.ConversationSearchResult;
import cn.wildfirechat.model.GroupInfo;
import cn.wildfirechat.model.UserInfo;

public class ConversationViewHolder extends ResultItemViewHolder<ConversationSearchResult> {
    @Bind(R.id.portraitImageView)
    ImageView portraitImageView;
    @Bind(R.id.nameTextView)
    TextView nameTextView;
    @Bind(R.id.descTextView)
    TextView descTextView;

    private UserViewModel userViewModel;
    private GroupViewModel groupViewModel;

    public ConversationViewHolder(Fragment fragment, View itemView) {
        super(fragment, itemView);
        ButterKnife.bind(this, itemView);

        userViewModel = ViewModelProviders.of(fragment).get(UserViewModel.class);
        groupViewModel = ViewModelProviders.of(fragment).get(GroupViewModel.class);
    }

    @Override
    public void onBind(String keyword, ConversationSearchResult conversationSearchResult) {
        Conversation conversation = conversationSearchResult.conversation;
        if (conversation.type == Conversation.ConversationType.Single) {
            UserInfo userInfo = userViewModel.getUserInfo(conversation.target, false);
            if (userInfo != null) {
                Glide.with(fragment).load(userInfo.portrait).apply(new RequestOptions().centerCrop().placeholder(R.mipmap.avatar_def)).into(portraitImageView);
                nameTextView.setText(userInfo.displayName);
            }
        } else {
            GroupInfo groupInfo = groupViewModel.getGroupInfo(conversation.target, false);
            if (groupInfo != null) {
                Glide.with(fragment).load(groupInfo.portrait).apply(new RequestOptions().placeholder(R.mipmap.ic_group_cheat).centerCrop()).into(portraitImageView);
                nameTextView.setText(groupInfo.name);
            }
        }

        if (conversationSearchResult.marchedMessage != null) {
            descTextView.setText(conversationSearchResult.marchedMessage.content.digest());
        } else {
            descTextView.setText(conversationSearchResult.marchedCount + "条记录");
        }
    }
}
