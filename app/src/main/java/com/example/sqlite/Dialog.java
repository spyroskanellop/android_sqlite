package com.example.sqlite;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Dialog extends AppCompatDialogFragment {

    EditText idText;
    private DialogListener listener;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Search id")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String id = idText.getText().toString();
                        listener.applyTexts(id);
                    }
                });
        idText = view.findViewById(R.id.idText);
        return builder.create();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            listener = (DialogListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException((context.toString() + "must implement ExampleDialogListener"));
        }
    }
    public interface DialogListener{
        void applyTexts(String id);
    }
}
