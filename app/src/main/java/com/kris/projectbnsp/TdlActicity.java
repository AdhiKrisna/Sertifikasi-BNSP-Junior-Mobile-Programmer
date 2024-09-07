package com.kris.projectbnsp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kris.projectbnsp.Adapter.ToDoAdapter;
import com.kris.projectbnsp.Model.ToDoModel;
import com.kris.projectbnsp.Utils.DatabaseHelper;
import com.kris.projectbnsp.Utils.RecyclerViewTouchHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TdlActicity extends AppCompatActivity implements OnDialogCloseListener {
    private RecyclerView mRecyclerview;
    private FloatingActionButton fab;
    private DatabaseHelper myDB;
    private List<ToDoModel> mList;
    private ToDoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tdl_acticity);
        Toolbar toolbar = findViewById(R.id.navbar);
        toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.white, getTheme()));
        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(TdlActicity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        mRecyclerview = findViewById(R.id.recyclerview);
        fab = findViewById(R.id.fab_add);
        myDB = new DatabaseHelper(TdlActicity.this);
        mList = new ArrayList<>();
        adapter = new ToDoAdapter(myDB , TdlActicity.this);

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapter);

        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapter.setTasks(mList);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager() , AddNewTask.TAG);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerview);
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapter.setTasks(mList);
        adapter.notifyDataSetChanged();
    }
}