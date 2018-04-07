package com.example.john.particiaption10sql2018;

/**
 * Created by John on 4/2/2018.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

//The commentDataSource class//
public class CommentDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMMENT };

    //method to set comment data source//
    public CommentDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    //method to open datebase//
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    //method to close database//
    public void close() {
        dbHelper.close();
    }

    //method to create comments//
    public Comment createComment(String comment) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
        long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);  //parameters: int insertId, string selection Args, string groupBy, string having, string orderBy//
        cursor.moveToFirst();
        Comment newComment = cursorToComment(cursor);
        cursor.close();
        return newComment;
    }
    //Above code will pulls data from table comments database to set comment//
    //method to delete comment//
    public void deleteComment(Comment comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);  //parameters: int id, string whereArgs//
    }
    //Above code will allow user to delete comment from the table comments database//
    //method to get comment list//
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, null, null, null, null, null); //parameters: string selection, string selection Args, string groupBy, string having, string orderBy//

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }
    //Above code to allow user to add comment to table comments database//
    //method to set comment data//
    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment();
        comment.setId(cursor.getLong(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ID)));
        comment.setComment(cursor.getString(1));
        return comment;
    }
    //Above code to set comment to display//
}
