package proj.plan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SubDialog.SubDialogListener, View.OnClickListener {

    private TextView tvMonday;
    private ListView lvMonday;
    private ArrayList<String> itemsMonday;
    private ArrayAdapter<String> itemsAdapterMonday;

    private TextView tvTuesday;
    private ListView lvTuesday;
    private ArrayList<String> itemsTuesday;
    private ArrayAdapter<String> itemsAdapterTuesday;

    private TextView tvWednesday;
    private ListView lvWednesday;
    private ArrayList<String> itemsWednesday;
    private ArrayAdapter<String> itemsAdapterWednesday;

    private TextView tvThursday;
    private ListView lvThursday;
    private ArrayList<String> itemsThursday;
    private ArrayAdapter<String> itemsAdapterThursday;

    private TextView tvFriday;
    private ListView lvFriday;
    private ArrayList<String> itemsFriday;
    private ArrayAdapter<String> itemsAdapterFriday;

    //selects the correct adapter to add a new topic
    private ArrayAdapter<String> choiceAdapter;

    private DayVisi day = new DayVisi();

    private ImageView ivAdd, ivAdd2, ivAdd3, ivAdd4, ivAdd5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //======================================================
        //opens the plan of the day of the week
        //======================================================
        tvMonday = findViewById(R.id.tvMonday);
        lvMonday = findViewById(R.id.lvMonday);
        lvMonday.setVisibility(View.GONE);

        tvMonday.setOnClickListener(this);

        tvTuesday = findViewById(R.id.tvTuesday);
        lvTuesday = findViewById(R.id.lvTuesday);
        lvTuesday.setVisibility(View.GONE);

        tvTuesday.setOnClickListener(this);

        tvWednesday = findViewById(R.id.tvWednesday);
        lvWednesday = findViewById(R.id.lvWednesday);
        lvWednesday.setVisibility(View.GONE);

        tvWednesday.setOnClickListener(this);

        tvThursday = findViewById(R.id.tvThursday);
        lvThursday = findViewById(R.id.lvThursday);
        lvThursday.setVisibility(View.GONE);

        tvThursday.setOnClickListener(this);

        tvFriday = findViewById(R.id.tvFriday);
        lvFriday = findViewById(R.id.lvFriday);
        lvFriday.setVisibility(View.GONE);

        tvFriday.setOnClickListener(this);

        //======================================================
        //Adapter settings
        //======================================================
        itemsMonday = new ArrayList<String>();
        itemsAdapterMonday = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsMonday);
        lvMonday.setAdapter(itemsAdapterMonday);
        itemsMonday.add("temp1");
        itemsMonday.add("temp2");
        itemsMonday.add("temp3");
        itemsMonday.add("temp4");
        itemsMonday.add("temp5");

        itemsTuesday = new ArrayList<String>();
        itemsAdapterTuesday = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsTuesday);
        lvTuesday.setAdapter(itemsAdapterTuesday);
        itemsTuesday.add("temp1");
        itemsTuesday.add("temp2");
        itemsTuesday.add("temp3");

        itemsWednesday = new ArrayList<String>();
        itemsAdapterWednesday = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsWednesday);
        lvWednesday.setAdapter(itemsAdapterWednesday);
        itemsWednesday.add("temp1");
        itemsWednesday.add("temp2");

        itemsThursday = new ArrayList<String>();
        itemsAdapterThursday = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsThursday);
        lvThursday.setAdapter(itemsAdapterThursday);
        itemsThursday.add("temp1");
        itemsThursday.add("temp2");

        itemsFriday = new ArrayList<String>();
        itemsAdapterFriday = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsFriday);
        lvFriday.setAdapter(itemsAdapterFriday);
        itemsFriday.add("temp1");
        itemsFriday.add("temp2");

        //======================================================
        //Adding a new activity to the plan
        //======================================================
        ivAdd = findViewById(R.id.ivAdd1);
        ivAdd2 = findViewById(R.id.ivAdd2);
        ivAdd3 = findViewById(R.id.ivAdd3);
        ivAdd4 = findViewById(R.id.ivAdd4);
        ivAdd5 = findViewById(R.id.ivAdd5);

        ivAdd.setOnClickListener(this);
        ivAdd2.setOnClickListener(this);
        ivAdd3.setOnClickListener(this);
        ivAdd4.setOnClickListener(this);
        ivAdd5.setOnClickListener(this);
    }

    public void openDialog()
    {
        SubDialog subDialog = new SubDialog();
        subDialog.show(getSupportFragmentManager(), "subject dialog");
    }

    @Override
    public void applyTexts(String Name, String Teacher, String Time, String Room) {
        choiceAdapter.add(Time + "    " + Name + "  " + Teacher + "  " + Room);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ivAdd1:
                openDialog();
                choiceAdapter = itemsAdapterMonday;
                break;
            case R.id.ivAdd2:
                openDialog();
                choiceAdapter = itemsAdapterTuesday;
                break;
            case R.id.ivAdd3:
                openDialog();
                choiceAdapter = itemsAdapterWednesday;
                break;
            case R.id.ivAdd4:
                openDialog();
                choiceAdapter = itemsAdapterThursday;
                break;
            case R.id.ivAdd5:
                openDialog();
                choiceAdapter = itemsAdapterFriday;
                break;
            case R.id.tvMonday:
                day.DayFunc(lvMonday);
                break;
            case R.id.tvTuesday:
                day.DayFunc(lvTuesday);
                break;
            case R.id.tvWednesday:
                day.DayFunc(lvWednesday);
                break;
            case R.id.tvThursday:
                day.DayFunc(lvThursday);
                break;
            case R.id.tvFriday:
                day.DayFunc(lvFriday);
                break;
        }
    }
}