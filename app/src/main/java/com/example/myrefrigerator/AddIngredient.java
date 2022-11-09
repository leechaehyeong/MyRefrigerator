package com.example.myrefrigerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddIngredient extends AppCompatActivity {

    Button ingredientAddBtn;
    EditText ingredientNameEditTxt, ingredientAmountEditTxt, ingredientDateEditTxt, ingredientInfoEditTxt;

    mySQLiteHelper mySQLiteHelper;
    SQLiteDatabase sqlDB;

    //SQL 객체 선언
    public class mySQLiteHelper extends SQLiteOpenHelper {
        public mySQLiteHelper(Context context) {
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE groupIngredient ( ingredientName TEXT, ingredientAmount INTEGER, ingredientDate INTEGER, ingredientInfo INTEGER );");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupIngredient");
            onCreate(db);

        }
    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

            //SQL 뭔지 모르겠음!
            mySQLiteHelper = new mySQLiteHelper(this);
            sqlDB = mySQLiteHelper.getWritableDatabase();
            mySQLiteHelper.onUpgrade(sqlDB, 1, 2);
            sqlDB.close();

            //요소 연결
           ingredientAddBtn = (Button) findViewById(R.id.ingredientAddBtn);
           ingredientNameEditTxt = (EditText) findViewById(R.id.ingredientNameEditTxt);
           ingredientAmountEditTxt = (EditText) findViewById(R.id.ingredientAmountEditTxt);
           ingredientDateEditTxt= (EditText) findViewById(R.id.ingredientDateEditTxt);
           ingredientInfoEditTxt= (EditText) findViewById(R.id.ingredientInfoEditTxt);

           //ingredientAddBtn 재료 추가 버튼 클릭
            ingredientAddBtn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    sqlDB = mySQLiteHelper.getWritableDatabase();
                    sqlDB.execSQL("INSERT INTO groupIngredient VALUES ( '"+ingredientNameEditTxt.getText().toString()+ "' , '"+ingredientAmountEditTxt.getText().toString()+ "', '"+ingredientDateEditTxt.getText().toString()+ "', '"+ingredientInfoEditTxt.getText().toString()+ "');");
                    sqlDB.close();
                }
            });
        }


}
