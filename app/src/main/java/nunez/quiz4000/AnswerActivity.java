package nunez.quiz4000;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import nunez.quiz4000.db.GamePlay;
import nunez.quiz4000.db.Question;

/**
 * Created by nunez on 2/27/2017.
 */

public class AnswerActivity extends Activity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);
        GamePlay currentGame = ((ChuckApplication)getApplication()).getCurrentGame();

        TextView results = (TextView)findViewById(R.id.answers);
        String answers = getAnswers(currentGame.getQuestions());
        results.setText(answers);

        //handle button actions
        Button finishBtn = (Button) findViewById(R.id.finishBtn);
        finishBtn.setOnClickListener(this);

    }
//take question list, return answers
    /**
     * Method to get set of answers for a list of questions
     * @param questions
     * @return
     */
    public static String getAnswers(List<Question> questions) {
        int question = 1;
        StringBuffer sb = new StringBuffer();

        for (Question q : questions){
            sb.append("Q").append(question).append(") ").append(q.getQuestion()).append("? \n");
            sb.append("Answer: ").append(q.getAnswer()).append("\n\n");
            question ++;
        }

        return sb.toString();
    }


    /* (non-Javadoc)
     * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
     *
     * This method is to override the back button on the phone
     * to prevent users from navigating back in to the quiz
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        switch (keyCode)
        {
            case KeyEvent.KEYCODE_BACK :
                return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.finishBtn :
                finish();
        }
    }

}