package br.com.natura.fiap.naturatododia.main;

import android.app.Fragment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.http.ServiceCallback;

import java.util.HashMap;
import java.util.Map;

import br.com.natura.fiap.naturatododia.R;
import br.com.natura.fiap.naturatododia.entity.ChatMessage;
import br.com.natura.fiap.naturatododia.utils.ChatArrayAdapter;

public class ChatFragment extends Fragment implements View.OnClickListener, TextWatcher{
    private EditText txtMsg;
    private ImageButton btnSendMsg;
    private ListView msgListView;
    private ChatArrayAdapter msgsAdapter;
    private boolean conversationFinished;
    private static ConversationService conversationService;
    // set the workspace id for WCS
    private String inputWorkspaceId;
    Map context = new HashMap();

    private Handler handler = new Handler();

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

        inputWorkspaceId = getString(R.string.conversation_workspaceId);
        conversationService = initConversationService();

        //msgsAdapter.add(new ChatMessage("Olá", true));
        //msgsAdapter.add(new ChatMessage("Teste", true));
        //msgsAdapter.add(new ChatMessage("Olá", false));

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSendMsg) {
            txtMsg.setText(txtMsg.getText().toString().trim());
            if (!TextUtils.isEmpty(txtMsg.getText())) {
                msgsAdapter.add(new ChatMessage(txtMsg.getText().toString(), false));
                MessageResponse response = null;
                conversationAPI(String.valueOf(txtMsg.getText()), context, inputWorkspaceId);
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

    private ConversationService initConversationService() {
        ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2017_04_21);
        String username = getString(R.string.conversation_username);
        String password = getString(R.string.conversation_password);
        service.setUsernameAndPassword(username, password);
        service.setEndPoint(getString(R.string.conversation_url));
        return service;
    }

    public void conversationAPI(String input, Map context, String workspaceId) {

        //conversationService
        MessageRequest newMessage = new MessageRequest.Builder()
                .inputText(input).context(context).build();

        //cannot use the following as it will attempt to run on the UI thread and crash
        //MessageResponse response = conversationService.message(workspaceId, newMessage).execute();

        //use the following so it runs on own async thread
        //then when get a response it calls displayMsg that will update the UI
        conversationService.message(workspaceId, newMessage).enqueue(new ServiceCallback<MessageResponse>() {
            @Override
            public void onResponse(MessageResponse response) {
                System.out.println(response);
                displayMsg(response);
            }
            @Override
            public void onFailure(Exception e) {
                //showError(e);
            }
        });
    }

    public void displayMsg(final MessageResponse msg)
    {
        final MessageResponse mssg=msg;
        handler.post(new Runnable() {

            @Override
            public void run() {

                //from the WCS API response
                //https://www.ibm.com/watson/developercloud/conversation/api/v1/?java#send_message
                //extract the text from output to display to the user
                for (int i = 0; i < mssg.getText().size(); i++) {
                    msgsAdapter.add(new ChatMessage(mssg.getText().get(i), true));
                }

                msgListView.smoothScrollToPosition(msgsAdapter.getCount() - 1);


                context = mssg.getContext();


            }
        });

    }
}
