package nirajmorar.senseyosoles;

import android.content.*;
import java.util.*;
import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.bluetooth.*;
import android.view.*;
import android.widget.*;
import java.io.*;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_ENABLE_BT = 1;
    private final static int REQUEST_ENABLE_VISIBILITY = 2;

    Button b1, b2, b3, b4;
    BluetoothAdapter btAdapter;
    BluetoothSocket btSocket;
    Set<BluetoothDevice> pairedDevices;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);

        btAdapter = BluetoothAdapter.getDefaultAdapter();
        lv = (ListView) findViewById(R.id.listView);

//        bluetoothStuff();
    }

    public void on(View v) {
        if(!btAdapter.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, REQUEST_ENABLE_BT);
            Toast.makeText(getApplicationContext(), "Turned on", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
        }
    }

    public void off(View v) {
        btAdapter.disable();
        Toast.makeText(getApplicationContext(), "Turned off", Toast.LENGTH_LONG).show();
    }

    public void visible(View v) {
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, REQUEST_ENABLE_VISIBILITY);
    }

    public void list(View v) {
        pairedDevices = btAdapter.getBondedDevices();

        ArrayList list = new ArrayList();

        for (BluetoothDevice bt: pairedDevices) {
            list.add(bt.getName());
        }

        Toast.makeText(getApplicationContext(), "Showing Paried Devices", Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        lv.setAdapter(adapter);
    }
}


/*
    private void bluetoothStuff() {
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        //Checks to see if the phone has bluetooth capabilities
        if (btAdapter != null) {
            //Checks to see if bluetooth is enabled
            if (!btAdapter.isEnabled()) {
                Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBTIntent, REQUEST_ENABLE_BT);
            }
        }
        else {
            //status.setText("Your device does not support Bluetooth");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int pairedFlag = 0;
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                //Going through paired devices before becoming discoverable
                pairedDevices = btAdapter.getBondedDevices();
                for (BluetoothDevice device: pairedDevices) {
                    String address = device.getAddress();
                    String name = device.getName();
                    //Get the modules Bluetooth IP here
                    if (address.equals("98:D3:36:00:C2:3F")) {
                        pairedFlag = 1;
                        btAdapter.cancelDiscovery();
                    }
                }

                if (pairedFlag == 0) {
                    Intent discoverableIntent = new Intent(btAdapter.ACTION_REQUEST_DISCOVERABLE);
                    discoverableIntent.putExtra(btAdapter.EXTRA_DISCOVERABLE_DURATION, 60);
                    startActivity(discoverableIntent);
                }

            }
        }
        else if (requestCode == 30) {
            if (resultCode == RESULT_OK) {

            }
        }
    }

}*/
