# ExtLog
A simple wrapper class for android log.

Sample code --

import android.app.Activity;

public class MainActivity extends Activity
{
        static private ExtLog elog = new ExtLog(ExtLog.VERBOSE, ExtLog.TurnOn);

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
                elog.d("run to the main entry ...");
                super.onCreate(savedInstanceState);
                
                ...
                
                int from = 0;
                int to = 5;
                elog.v("start from(%d) to(%d)", from, to);
                elog.assertTrue(from < to, "from(%d) < to(%d)", from, to);
                
                String str = "test".
                elog.assertNotNull(str, "str");
        }
}
