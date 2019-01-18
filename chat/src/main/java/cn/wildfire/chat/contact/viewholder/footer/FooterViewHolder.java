package cn.wildfire.chat.contact.viewholder.footer;

import androidx.lifecycle.ViewModelProviders;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import cn.wildfire.chat.contact.ContactAdapter;
import cn.wildfire.chat.contact.ContactViewModel;
import cn.wildfire.chat.contact.model.FooterValue;

public abstract class FooterViewHolder<T extends FooterValue> extends RecyclerView.ViewHolder {
    protected Fragment fragment;
    protected ContactAdapter adapter;
    protected ContactViewModel contactViewModel;

    public FooterViewHolder(Fragment fragment, ContactAdapter adapter, View itemView) {
        super(itemView);
        this.fragment = fragment;
        this.adapter = adapter;
        contactViewModel = ViewModelProviders.of(fragment).get(ContactViewModel.class);
    }


    public abstract void onBind(T t);

}
