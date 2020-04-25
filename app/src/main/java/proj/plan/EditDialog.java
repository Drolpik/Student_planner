package proj.plan;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class EditDialog extends AppCompatDialogFragment {

    private EditText subName, subTeacher, subTime, subType, subRoom;

    private String editName, editTeacher, editTime, editType, editRoom;

    private EditDialogListener listener;

    public EditDialog(ArrayList<PlanItem> choiceListAction, int position_item) {
        editName = choiceListAction.get(position_item).getmTextSubject();
        editTeacher = choiceListAction.get(position_item).getmTextTeacher();
        editTime = choiceListAction.get(position_item).getmTextTime();
        editType = choiceListAction.get(position_item).getmTextType();
        editRoom = choiceListAction.get(position_item).getmTextRoom();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle(R.string.edit_subject)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String Name = subName.getText().toString();
                        String Teacher = subTeacher.getText().toString();
                        String Time = subTime.getText().toString();
                        String Type = subType.getText().toString();
                        String Room = subRoom.getText().toString();
                        listener.saveEditTexts(Name, Teacher, Time, Type, Room);
                        Toast.makeText(getContext(), R.string.all_save_toast, Toast.LENGTH_SHORT).show();
                    }
                });

        subName = view.findViewById(R.id.subName);
        subTeacher = view.findViewById(R.id.subTeacher);
        subTime = view.findViewById(R.id.subTime);
        subType = view.findViewById(R.id.subType);
        subRoom = view.findViewById(R.id.subRoom);

        subName.setText(editName);
        subTeacher.setText(editTeacher);
        subTime.setText(editTime);
        subType.setText(editType);
        subRoom.setText(editRoom);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (EditDialog.EditDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement EditDialogListener");
        }
    }

    public interface EditDialogListener
    {
        void saveEditTexts(String Name, String Teacher, String Time, String Type, String Room);
    }
}