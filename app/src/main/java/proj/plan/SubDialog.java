package proj.plan;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SubDialog extends AppCompatDialogFragment {

    /**
     * Teksty przekazywne do okienka dodawania
     */
    private EditText subName, subTeacher, subTime, subType, subRoom;

    private SubDialogListener listener;

    /**
     * Tworzy okienko dodawania przedmiotu
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle(R.string.new_subject)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String Name = subName.getText().toString();
                        String Teacher = subTeacher.getText().toString();
                        String Time = subTime.getText().toString();
                        String Type = subType.getText().toString();
                        String Room = subRoom.getText().toString();
                        listener.applyTexts(Name, Teacher, Time, Type, Room);
                        Toast.makeText(getContext(), R.string.add_toast, Toast.LENGTH_SHORT).show();
                    }
                });

        subName = view.findViewById(R.id.subName);
        subTeacher = view.findViewById(R.id.subTeacher);
        subTime = view.findViewById(R.id.subTime);
        subType = view.findViewById(R.id.subType);
        subRoom = view.findViewById(R.id.subRoom);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (SubDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement SubDialogListener");
        }
    }

    /**
     * Przekazuje dane do MainActivity
     */
    public interface SubDialogListener
    {
        void applyTexts(String Name, String Teacher, String Time, String Type, String Room);
    }
}
