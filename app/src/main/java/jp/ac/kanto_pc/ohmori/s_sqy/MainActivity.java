package jp.ac.kanto_pc.ohmori.s_sqy;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	SQLiteDatabase db;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DatabaseHelper dHelper = new DatabaseHelper(this);
		db = dHelper.getReadableDatabase();

		setContentView(R.layout.activity_main);
		Query();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		db.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		/* アクションバーへの登録 （Android3.0以降） */
		MenuItem item1 = menu.add(1, 11, 0, "表示");
		item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);	// ALWAYS/IF_ROOM/NEVER
		MenuItem item2 = menu.add(1, 12, 0, "追加");
		item2.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);

		switch (item.getItemId()) {
			case 11:
				setContentView(R.layout.activity_main);
				Query();
				break;
			case 12:
				setContentView(R.layout.activity_sub);
				Insert();
				break;
		}

		return true;
	}


	void Query() {
		try {
			TableLayout tl = (TableLayout) findViewById(R.id.table);
			String[] columns = {"rowid", "name", "age", "phone"};
			Cursor myCursor = db.query("meibo", columns, null, null, null, null, null);
			while (myCursor.moveToNext()) {
				TextView tv1 = new TextView(this);
				tv1.setText(myCursor.getString(0));
				TextView tv2 = new TextView(this);
				tv2.setText(myCursor.getString(1));
				TextView tv3 = new TextView(this);
				tv3.setText("" + myCursor.getInt(2));
				TextView tv4 = new TextView(this);
				tv4.setText("" + myCursor.getString(3));
				TableRow tr = new TableRow(this);
				tr.addView(tv1);
				tr.addView(tv2);
				tr.addView(tv3);
				tr.addView(tv4);
				tl.addView(tr, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void Insert() {
		Button bt = (Button) findViewById(R.id.enter);
		final EditText et_name = (EditText) findViewById(R.id.name);
		final EditText et_phone = (EditText) findViewById(R.id.phone);
		final EditText et_address = (EditText) findViewById(R.id.address);
		final EditText et_age = (EditText) findViewById(R.id.age);

		bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = et_name.getText().toString();
				String phone = et_phone.getText().toString();
				String address = et_address.getText().toString();
				String age = et_age.getText().toString();

				if (name.length() > 0) {
					db.execSQL("insert into meibo (name, phone, address, age) values ('"
							+ name + "','" + phone + "','" + address + "'," + age + ")");
					et_name.setText("");
					et_phone.setText("");
					et_address.setText("");
					et_age.setText("");
				}
			}
		});
	}
}

