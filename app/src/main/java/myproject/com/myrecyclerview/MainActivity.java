package myproject.com.myrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //variables
    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<String> imageurl = new ArrayList<>();
    ArrayList<String> noticetitle;
    ArrayList<String> urlofnotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();

        noticetitle=new ArrayList<>();
        urlofnotice=new ArrayList<>();

    }

    //method for setting up the recyclerview
    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(title, imageurl, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference db = firebaseDatabase.getReference();
        DatabaseReference userref = db.child("Images").child("URL");

        userref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                doyourwork( dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                doyourwork( dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                doyourwork(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                doyourwork(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void doyourwork(DataSnapshot dataSnapshot)
    {


        ItemsClass nticegetter =dataSnapshot.getValue(ItemsClass.class);
        String noticetitlemain=nticegetter.getTitle();
        String noticeurlofnotice=nticegetter.getImageurl();

        noticetitle.add(noticetitlemain);
        urlofnotice.add(noticeurlofnotice);


    }

}
