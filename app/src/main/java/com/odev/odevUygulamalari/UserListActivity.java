package com.odev.odevUygulamalari;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.odevUygulamalari.R;
import java.util.List;


public class UserListActivity extends AppCompatActivity {

    private RecyclerView recycler_view;
    private List<Person> person_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlist_main);


        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        recycler_view.setLayoutManager(layoutManager);
        person_list = new UserData().getUserData();


        SimpleRecyclerAdapter adapter_items = new SimpleRecyclerAdapter(person_list, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Person person = person_list.get(position);
            }
        });
        recycler_view.setHasFixedSize(true);

        recycler_view.setAdapter(adapter_items);

        recycler_view.setItemAnimator(new DefaultItemAnimator());


    }


}
