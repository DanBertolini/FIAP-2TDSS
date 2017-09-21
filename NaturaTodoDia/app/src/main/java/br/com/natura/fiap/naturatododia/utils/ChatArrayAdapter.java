package br.com.natura.fiap.naturatododia.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.natura.fiap.naturatododia.R;
import br.com.natura.fiap.naturatododia.entity.ChatMessage;

public class ChatArrayAdapter extends ArrayAdapter<ChatMessage> {

    private TextView chatText;
    private List<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
    private Context context;

    @Override
    public void add(ChatMessage object) {
        chatMessageList.add(object);
        super.add(object);
    }

    public ChatArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }

    public int getCount() {
        return this.chatMessageList.size();
    }

    public ChatMessage getItem(int index) {
        return this.chatMessageList.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage chatMessageObj = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (!chatMessageObj.isBotMessage()) {
            row = inflater.inflate(R.layout.customer_side_conversation, parent, false);
        }else{
            row = inflater.inflate(R.layout.bot_side_conversation, parent, false);
        }
        chatText = (TextView) (chatMessageObj.isBotMessage() ? row.findViewById(R.id.bot_bubble) : row.findViewById(R.id.customer_bubble));
        chatText.setText(chatMessageObj.getMessage());
        return row;
    }
}
