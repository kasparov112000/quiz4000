package nunez.quiz4000;

/**
 * Created by nunez on 2/26/2017.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**

        import android.app.Activity;
        import android.os.Bundle;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;

/**
 * @author robert.hinds
 *
 */
public class RulesActivity extends Activity implements OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules);

        //finish button
        Button backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        /**
         * if the back button is clicked then go back to the main menu
         */
        finish();
    }

}