package com.ieg.ders3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendMsgFragment extends Fragment implements Button.OnClickListener {

    private EditText edPhone;
    private EditText edMsg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_msg_send, container, false);

        edPhone = (EditText) view.findViewById(R.id.ed_phone_number);
        edMsg = (EditText) view.findViewById(R.id.ed_msg);
        Button btnSend = (Button) view.findViewById(R.id.btn_send);
        btnSend.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send: {
                String phone = edPhone.getText().toString();
                String msg = edMsg.getText().toString();

                if (msg.length() <= 0) {
                    Toast.makeText(getContext(), "Bir mesaj yazın !", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!Helper.isValidPhoneNumber(phone)) {
                    Toast.makeText(getContext(), "Telefon numarası formattı doğru değil !", Toast.LENGTH_SHORT).show();
                    return;
                }

                Helper.sendDebugSms(phone, msg);
                break;
            }
            default:
                break;
        }
    }
}
