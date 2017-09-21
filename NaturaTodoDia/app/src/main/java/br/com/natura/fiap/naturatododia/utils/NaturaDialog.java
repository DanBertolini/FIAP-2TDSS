package br.com.natura.fiap.naturatododia.utils;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class NaturaDialog {
    public Runnable ans_true = null;
    public Runnable ans_false = null;

    // Dialog. --------------------------------------------------------------

    public boolean Confirm(Activity act, String Title, String ConfirmText,
                           String CancelBtn, String OkBtn, Runnable aProcedure, Runnable bProcedure) {
        ans_true = aProcedure;
        ans_false = bProcedure;
        AlertDialog dialog = new AlertDialog.Builder(act).create();
        dialog.setTitle(Title);
        dialog.setMessage(ConfirmText);
        dialog.setCancelable(false);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, OkBtn,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int buttonId) {
                        ans_true.run();
                    }
                });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, CancelBtn,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int buttonId) {
                        ans_false.run();
                    }
                });
        //EditText txtNomeSugestao = new EditText(act);
        //dialog.setView(txtNomeSugestao);
        dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.show();
        return true;
    }

    public boolean InputDialog(Activity act, String Title, String ConfirmText,
                               String CancelBtn, String OkBtn, final InputResults initialText, Runnable aProcedure, Runnable bProcedure) {
        ans_true = aProcedure;
        ans_false = bProcedure;
        AlertDialog dialog = new AlertDialog.Builder(act).create();
        dialog.setTitle(Title);
        dialog.setMessage(ConfirmText);
        dialog.setCancelable(false);
        RelativeLayout relativeLayout = new RelativeLayout(act);
        relativeLayout.setMinimumWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        relativeLayout.setPadding(60, 0, 20, 0);

        final EditText txtInput = new EditText(act);
        String initialTextValue = (String) initialText.getValue();
        txtInput.setText(initialTextValue);
        relativeLayout.addView(txtInput);
        dialog.setView(relativeLayout);

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, OkBtn,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int buttonId) {
                        initialText.setValue(txtInput.getText().toString());
                        ans_true.run();
                    }
                });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, CancelBtn,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int buttonId) {
                        ans_false.run();
                    }
                });

        dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.show();
        return true;
    }
}
