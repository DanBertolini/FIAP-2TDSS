package br.com.natura.fiap.naturatododia.main;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.ListView;

import br.com.natura.fiap.naturatododia.R;
import br.com.natura.fiap.naturatododia.entity.ChatMessage;
import br.com.natura.fiap.naturatododia.utils.ChatArrayAdapter;

public class ChatFragment extends Fragment implements View.OnClickListener, TextWatcher{
    private EditText txtMsg;
    private ImageButton btnSendMsg;
    private ListView msgListView;
    private ChatArrayAdapter msgsAdapter;
    private boolean conversationFinished;

    public ChatFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chat_layout, container, false);

        msgListView = (ListView) v.findViewById(R.id.msgListView);
        msgsAdapter = new ChatArrayAdapter(v.getContext(), R.layout.customer_side_conversation);
        msgListView.setAdapter(msgsAdapter);
        txtMsg = (EditText) v.findViewById(R.id.txtMsg);
        txtMsg.addTextChangedListener(this);
        btnSendMsg = (ImageButton) v.findViewById(R.id.btnSendMsg);
        btnSendMsg.setOnClickListener(this);

        msgsAdapter.add(new ChatMessage("Olá", true));
        msgsAdapter.add(new ChatMessage("Teste", true));
        msgsAdapter.add(new ChatMessage("Olá", false));

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSendMsg) {
            txtMsg.setText(txtMsg.getText().toString().trim());
            if (!TextUtils.isEmpty(txtMsg.getText())) {
                msgsAdapter.add(new ChatMessage(txtMsg.getText().toString(), false));
                txtMsg.setText("");
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String msg = txtMsg.getText().toString();
        if (msg.equals("") || msg.matches("[\\n\\r]+")) {
            btnSendMsg.setVisibility(View.INVISIBLE);
        } else{
            btnSendMsg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public boolean isConversationFinished(){
        return this.conversationFinished;
    }


}
