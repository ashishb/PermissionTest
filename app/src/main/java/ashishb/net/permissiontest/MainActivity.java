package ashishb.net.permissiontest;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.storage_permission_info);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                final String text = "Storage permission enabled: " +
                        (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
                        MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE));
                Log.i("storage-permission", text);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(text);
                    }
                });
            }
        };
        Timer timer = new Timer("timer");
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
