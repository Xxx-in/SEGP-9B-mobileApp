package medPal.App.PillReminder;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import medPal.App.R;

import static medPal.App.PillReminder.PillReminderFragment.UPDATE_PILL_REMINDER_REQUEST_CODE;

/**
 * Activity to let user select an existing pill reminder to be edited.
 */
public class EditPillReminderActivity extends AppCompatActivity {

    ArrayList<PillReminder> prList = new ArrayList<>();
    LocalTime time;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pill_reminder);
        getSupportActionBar().setTitle("Edit Pill Reminder");

        String t = getIntent().getStringExtra("TimeLabel");
        prList = (ArrayList<PillReminder>) getIntent().getSerializableExtra("PillReminders");

        int h = Integer.parseInt(t.substring(0,2));
        int m = Integer.parseInt(t.substring(3));
        time = LocalTime.of(h,m);

        TextView timeTitle = (TextView) findViewById(R.id.editPillReminderTime);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm a");
        timeTitle.setText(time.format(df));

        EditPillReminderAdapter adapter = new EditPillReminderAdapter(this, prList);
        ListView list = (ListView)findViewById(R.id.editPillReminderListView);
        list.setAdapter(adapter);
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,prList.size()*280);
        list.setLayoutParams(param);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startEditPillReminderDetailActivity(prList.get(position));
            }
        });
    }

    /**
     * After user selected (clicked) a pill reminder, bring the user to EditPillReminderDetail activity to edit the selected pill reminder.
     * @param pr The selected pill reminder instance.
     */
    public void startEditPillReminderDetailActivity(PillReminder pr) {
        Intent editPillReminderDetail = new Intent(this,EditPillReminderDetail.class);
        editPillReminderDetail.putExtra("PillReminderObj",pr);
        startActivityForResult(editPillReminderDetail, UPDATE_PILL_REMINDER_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPDATE_PILL_REMINDER_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        }
    }
}