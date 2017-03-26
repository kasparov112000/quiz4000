package nunez.quiz4000;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.io.IOException;

import java.util.List;

import nunez.quiz4000.utils.Constants;
import nunez.quiz4000.db.DBHelper;
import nunez.quiz4000.db.GamePlay;
import nunez.quiz4000.db.Question;

public class MainActivity extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //////////////////////////////////////////////////////////////////////
        //////// GAME MENU  /////////////////////////////////////////////////
        Button playBtn = (Button) findViewById(R.id.playBtn);
        playBtn.setOnClickListener(this);
        Button settingsBtn = (Button) findViewById(R.id.settingsBtn);
        settingsBtn.setOnClickListener(this);
        Button rulesBtn = (Button) findViewById(R.id.rulesBtn);
        rulesBtn.setOnClickListener(this);
        Button exitBtn = (Button) findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(this);
    }

    /**
     * Listener for game menu over ride on click method from my chuck app
     */
    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.playBtn :
                //once logged in, load the main page
                //Log.d("LOGIN", "User has started the game");

                //Get Question set //
                List<Question> questions = getQuestionSetFromDb();

                //Initialise Game with retrieved question set ///
                GamePlay c = new GamePlay();
                c.setQuestions(questions);
                c.setNumRounds(getNumQuestions());
                ((ChuckApplication)getApplication()).setCurrentGame(c);

                //Start Game Now.. //
                i = new Intent(this, QuestionActivity.class);
                startActivityForResult(i, Constants.PLAYBUTTON);
                break;

            case R.id.rulesBtn :
                i = new Intent(this, RulesActivity.class);
                startActivityForResult(i, Constants.RULESBUTTON);
                break;

            case R.id.settingsBtn :
                i = new Intent(this, SettingsActivity.class);
                startActivityForResult(i, Constants.SETTINGSBUTTON);
                break;

            case R.id.exitBtn :
                finish();
                break;
        }

    }
    /**
     * Method that retrieves a random set of questions from
     * the database for the given difficulty
     * @return
     * @throws Error
     */
    private List<Question> getQuestionSetFromDb() throws Error {
        int diff = getDifficultySettings();
        int numQuestions = getNumQuestions();
        DBHelper myDbHelper = new DBHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        }catch(SQLException sqle){
            throw sqle;
        }
        List<Question> questions = myDbHelper.getQuestionSet(diff, numQuestions);
        myDbHelper.close();
        return questions;
    }



    /**
     * Method to return the number of questions for the game
     * @return
     */
    private int getNumQuestions() {
        SharedPreferences settings = getSharedPreferences(Constants.SETTINGS, 0);
        int numRounds = settings.getInt(Constants.NUM_ROUNDS, 20);
        return numRounds;
    }
    /**
     * Method to return the difficulty settings
     * @return
     */
    private int getDifficultySettings() {
        SharedPreferences settings = getSharedPreferences(Constants.SETTINGS, 0);
        int diff = settings.getInt(Constants.DIFFICULTY, Constants.MEDIUM);
        return diff;
    }




}
