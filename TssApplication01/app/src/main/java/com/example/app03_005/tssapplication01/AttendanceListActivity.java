package com.example.app03_005.tssapplication01;

import android.app.ExpandableListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import android.content.Intent;

public class AttendanceListActivity extends ExpandableListActivity {

    List<Map<String, String>> groupList;
    List<List<Map<String, String>>> childList;

    String loginID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list);

        Intent intent = getIntent();
        loginID = intent.getStringExtra("loginID");

        makeExpandableList();
    }

    /**
     * ExpandableList 初期設定
     */
    private void makeExpandableList() {
        groupList = new ArrayList<>();
        childList = new ArrayList<>();

        int groupIndex = 3;
        int childIndex = 5;

        String groupKey = "GROUP_TITLE";
        String childKey1 = "CHILD_TITLE";
        String childKey2 = "SUMMARY";

        for (int i = 0; i < groupIndex; i++) {
            //親リスト
            Map<String, String> groupElement = new HashMap<>();
            groupElement.put(groupKey, "Group " + i);
            groupList.add(groupElement);

            //子リスト
            List<Map<String, String>> childElement = new ArrayList<>();
            for (int j = 0; j < childIndex; j++) {
                Map<String, String> child = new HashMap<>();
                child.put(childKey1, "Child " + j);
                child.put(childKey2, "Summary " + j);
                childElement.add(child);
            }
            childList.add(childElement);
        }

        SimpleExpandableListAdapter simpleExpandableListAdapter = new SimpleExpandableListAdapter(
                this,
                groupList,
                R.layout.row_title,
                new String[] {groupKey},
                new int[] {R.id.row_title_text_view},
                childList,
                R.layout.row_element,
                new String[] {childKey1, childKey2},
                new int[] {R.id.row_element_text_view1, R.id.row_element_text_view2, R.id.row_element_text_view3}
        );

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.attendance_expandable_list);
        expandableListView.setAdapter(simpleExpandableListAdapter);


    }

}
