package medPal.App;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PillReminderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PillReminderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PillReminderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PillReminderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PillReminderFragment newInstance(String param1, String param2) {
        PillReminderFragment fragment = new PillReminderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pill_reminder, container, false);

        PillReminderController prController = new PillReminderController();
        ArrayList<PillReminder> pillreminders = prController.getAllPillReminder();

        String result = "Result: \n";
        for(int i=0; i<pillreminders.size(); i++){
            result += pillreminders.get(i).toString();
        }
        Log.v("Test backend",result);

        /* Get image
        ImageView iV = (ImageView) v.findViewById(R.id.imageViewId);
        Picasso.get().load(prController.getMedicineById(2003).getImagePath()).into(iV);
        */

        return v;
    }
}