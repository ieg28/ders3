package com.ieg.ders3;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListMsgFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<SMSModel> smsList = null;

    class SMSListAdapter extends BaseAdapter {
        List<SMSModel> smsModelList;
        Context context;

        public SMSListAdapter(Context context, List<SMSModel> smsModelList) {
            this.context = context;
            this.smsModelList = smsModelList;
        }

        @Override
        public int getCount() {
            return smsModelList.size();
        }

        @Override
        public SMSModel getItem(int i) {
            return smsModelList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view1 = inflater.inflate(R.layout.view_row_sms, viewGroup, false);

            ((TextView) view1.findViewById(R.id.row_txt_title)).setText(smsModelList.get(i).title);
            ((TextView) view1.findViewById(R.id.row_txt_body)).setText(smsModelList.get(i).body);

            return view1;
        }
    }

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_msg_list, container, false);

        listView = (ListView) view.findViewById(R.id.lst_sms);
        SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.lst_refresh);

        refreshLayout.setOnRefreshListener(this);

        smsList = MainActivity.baseController.getSmsDB();
        if (smsList != null)
            listView.setAdapter(new SMSListAdapter(getContext(), smsList));
        else
            Toast.makeText(getContext(), "SMS list null", Toast.LENGTH_LONG).show();

        return view;
    }

    @Override
    public void onRefresh() {
        smsList = MainActivity.baseController.getSmsDB();
        if (smsList != null)
            listView.setAdapter(new SMSListAdapter(getContext(), smsList));
        else
            Toast.makeText(getContext(), "SMS list null", Toast.LENGTH_LONG).show();
    }
}
