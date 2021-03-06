package medPal.App.Homepage.NextPillReminder;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeMap;

import medPal.App.PillReminder.EditPillReminderActivity;
import medPal.App.PillReminder.PillReminder;
import medPal.App.PillReminder.PillReminderAdapter;
import medPal.App.PillReminder.PillReminderController;
import medPal.App.PillReminder.PillReminderTimeAdapter;
import medPal.App.R;

/***
 * Adapter to handle the view of the Next Pill Reminder at homepage
 */
public class NextPillReminderAdapter implements ExpandableListAdapter {
    private Context context;
    private ArrayList<LocalTime> time = new ArrayList<LocalTime>();
    private TreeMap<LocalTime, ArrayList<PillReminder>> prByTime = new TreeMap<>();
    private ArrayList<LocalTime> prByTimeKeySet = new ArrayList<>();
    private PillReminderController prController;

    /***
     * Constructor for  NextPillReminderAdapter
     * @param context Contex
     * @param time Pill Reminder Time List
     * @param prByTime Pill Reminder List
     * @param prController Pill Rmeinder Controller
     */
    public NextPillReminderAdapter(Context context, ArrayList<LocalTime> time, TreeMap<LocalTime, ArrayList<PillReminder>> prByTime, PillReminderController prController) {
        this.context = context;
        this.time = time;
        this.prByTime = prByTime;
        this.prController = prController;
        keyIndexing(); //Bind keyset of prByTime by index so we can access it easier (need it later)
    }

    public void keyIndexing() {
        for(LocalTime t : prByTime.keySet()){
            prByTimeKeySet.add(t);
        }
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return time.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return time.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        NextPillReminderAdapter.ParentHolder parentHolder = null;

        LocalTime time = (LocalTime) getGroup(groupPosition);

        if(convertView == null) {
            LayoutInflater userInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = userInflater.inflate(R.layout.home_pill_reminder, null);
            convertView.setHorizontalScrollBarEnabled(true);

            parentHolder = new NextPillReminderAdapter.ParentHolder();
            convertView.setTag(parentHolder);

        } else {
            parentHolder = (NextPillReminderAdapter.ParentHolder) convertView.getTag();
        }

        parentHolder.timeLabel = (TextView) convertView.findViewById(R.id.pillReminderTime);
        parentHolder.timeLabel.setText(to12Format(time));



        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        NextPillReminderAdapter.ChildHolder childHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_groupchild_pill_reminder, parent, false);
            childHolder = new NextPillReminderAdapter.ChildHolder();
            convertView.setTag(childHolder);
        }
        else {
            childHolder = (NextPillReminderAdapter.ChildHolder) convertView.getTag();
        }

        childHolder.horizontalListView = (RecyclerView) convertView.findViewById(R.id.prLists);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        childHolder.horizontalListView.setLayoutManager(layoutManager);

        PillReminderAdapter horizontalListAdapter = new PillReminderAdapter(context, prByTime.get(prByTimeKeySet.get(groupPosition)));
        childHolder.horizontalListView.setAdapter(horizontalListAdapter);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    private static class ChildHolder {
        static RecyclerView horizontalListView;
    }

    private static class ParentHolder {
        TextView timeLabel;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String to12Format(LocalTime t){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm a");
        return t.format(df);
    }
}
