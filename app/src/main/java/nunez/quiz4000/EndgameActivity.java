package nunez.quiz4000;

/**
 * Created by nunez on 2/27/2017.
 */

/**
 *
 */


        import android.app.Activity;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.view.KeyEvent;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

        import nunez.quiz4000.db.GamePlay;
        import nunez.quiz4000.utils.Constants;


/**
 * @author robert.hinds
 *
 */
public class EndgameActivity extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endgame);
        GamePlay currentGame = ((ChuckApplication)getApplication()).getCurrentGame();
        String result = "You Got " + currentGame.getRight() + "/" + currentGame.getNumRounds() + ".. ";
        String comment = getResultComment(currentGame.getRight(), currentGame.getNumRounds(), getDifficultySettings());


        TextView results = (TextView)findViewById(R.id.endgameResult);
        results.setText(result + comment);

        int image = getResultImage(currentGame.getRight(), currentGame.getNumRounds(), getDifficultySettings());
        ImageView resultImage = (ImageView)findViewById(R.id.resultPage);
        resultImage.setImageResource(image);

        //handle button actions
        Button finishBtn = (Button) findViewById(R.id.finishBtn);
        finishBtn.setOnClickListener(this);
        Button answerBtn = (Button) findViewById(R.id.answerBtn);
        answerBtn.setOnClickListener(this);

    }
    /**
     * Method to return an image to use for the end of game screen
     *
     * @param numCorrect - number of correct answers
     * @param numRounds - number of rounds
     * @param diff - difficulty level
     * @return int Image ID
     */
    public static int getResultImage(int numCorrect, int numRounds, int diff){
        //calculate percentage
        int percentage = calculatePercentage(numCorrect, numRounds);

        //work out which image
        if (percentage > 90){
            if (diff == Constants.EXTREME){
                return R.drawable.hard_winner;
            }else{
                return R.drawable.easy_winner;
            }
        }else if (percentage >= 80){
            return R.drawable.easy_winner;
        }else if (percentage >= 60){
            return R.drawable.easy_winner;
        }else if (percentage >= 40){
            return R.drawable.easy_winner;
        }else{
            return R.drawable.easy_winner;
        }
    }

    /**
     * Method to return the difficulty settings
     * @return
     */
    private int getDifficultySettings() {
        SharedPreferences settings = getSharedPreferences(Constants.SETTINGS, 0);
        int diff = settings.getInt(Constants.DIFFICULTY, 2);
        return diff;
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
                break;

            case R.id.answerBtn :
                Intent i = new Intent(this, AnswerActivity.class);
                startActivityForResult(i, Constants.PLAYBUTTON);
                break;
        }
    }




    /**
     * This method selects a end game response based on the players score
     * and current difficulty level
     *
     * @param numCorrect - num correct answers
     * @param numRounds - number of questions
     * @param diff - the difficulty level
     * @return String comment
     */
    public static String getResultComment(int numCorrect, int numRounds, int diff)
    {
        String comm="";
        int percentage = calculatePercentage(numCorrect, numRounds);
        switch (diff)
        {
            case Constants.EXTREME :
                if (percentage > 90){
                    comm = "You downloaded the Intersect!";
                }else if (percentage >= 80){
                    comm="Nicely Done!";
                }else if (percentage >= 60){
                    comm="Not too bad..";
                }else if (percentage >= 40){
                    comm="Well, don't give up..";
                }else{
                    comm="Next time, stay in the car..";
                }
                break;

            default:
                if (percentage > 90){
                    comm = "Awesome!";
                }else if (percentage >= 80){
                    comm="Nicely Done!";
                }else if (percentage >= 60){
                    comm="Not too bad..";
                }else if (percentage >= 40){
                    comm="Well, don't give up..";
                }else{
                    comm="Next time, stay in the car..";
                }
        }

        return comm;
    }
    /**
     * Calculate the percentage result based on the number correct and number of questions
     *
     * @param numCorrect - number of questions right
     * @param numRounds - total number of questions
     * @return int percentage correct
     */
    private static int calculatePercentage(int numCorrect, int numRounds) {
        double frac = (double)numCorrect/(double)numRounds;
        int percentage = (int) (frac*100);
        return percentage;
    }
}


