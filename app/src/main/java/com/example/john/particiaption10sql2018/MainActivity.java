package com.example.john.particiaption10sql2018;



import java.util.List;
        import java.util.Random;

        import android.app.ListActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ArrayAdapter;
//The main activity class//
public class MainActivity extends ListActivity {
    private CommentDataSource datasource;
    //method to call the data from the database//
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datasource = new CommentDataSource(this);
        datasource.open();

        List<Comment> values = datasource.getAllComments();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    //method to to add comment or delete comment//
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
        Comment comment = null;
        switch (view.getId()) {             //Switch statement to add or delete comment//
            case R.id.add:
                String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
                int nextInt = new Random().nextInt(3);
                // save the new comment to the database
                comment = datasource.createComment(comments[nextInt]);
                adapter.add(comment);
                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    comment = (Comment) getListAdapter().getItem(0);
                    datasource.deleteComment(comment);
                    adapter.remove(comment);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }
    //method to resume app//
    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }
    //method to pause app//
    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}