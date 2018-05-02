import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.vaigai.queen.settings;

/**
 * Created by Vaigai on 3/14/2018.
 */


public class AlarmReceiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service1 = new Intent(context, settings.class);
        service1.setData((Uri.parse("custom://" + System.currentTimeMillis())));
        context.startService(service1);
    }

}
