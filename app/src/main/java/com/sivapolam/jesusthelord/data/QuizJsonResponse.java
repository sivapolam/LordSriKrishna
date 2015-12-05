package com.sivapolam.jesusthelord.data;

import android.content.Context;
import android.util.Log;

import com.sivapolam.jesusthelord.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class QuizJsonResponse implements Constants
{
    private static final String TAG = QuizJsonResponse.class.getName();
    private JSONObject mJsonResponse;
    private Context mContext;
    private ArrayList<QuizModel> quizModelArrayList;
    private ArrayList<Integer> quizModelsAttemptedList = new ArrayList<Integer>();

    public ArrayList<Integer> getQuizModelsAttemptedList() {
        return quizModelsAttemptedList;
    }

    public void setQuizModelsAttemptedList(int item) {

        this.quizModelsAttemptedList.add(item);
    }

    private static QuizJsonResponse instance = null;

    private QuizJsonResponse() {
    }

    public static QuizJsonResponse getInstance() {
        if (instance == null) {
            synchronized (QuizJsonResponse.class) {
                if (instance == null) {
                    instance = new QuizJsonResponse();
                }
            }
        }
        return instance;
    }

    public JSONObject getJsonResponse() {
        return mJsonResponse;
    }

    public void readQuizJson(Context context) {
            this.mContext = context;
            this.readFromFile();
    }

    public int getScreensTotalCount() {
        int screensTotalCount = getQuizModulesList().size();
        return --screensTotalCount;
    }

    public void setJsonResponse(JSONObject mJsonResponse) {
        this.mJsonResponse = mJsonResponse;
    }

    public ArrayList<String> getQuizModulesList()
    {
        ArrayList<String> list = new ArrayList<String>();
        JSONArray jsonArray = mJsonResponse.optJSONArray(QUIZ_JSON_MODULES_LIST);

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                list.add(""+jsonArray.get(i));
            } catch (JSONException e) {
                Log.e(TAG, "Exception While converting JSONArray to ArrayList---"+e.toString());
            }

        }

        return list;
    }

    public void setQuizQuestions(String moduleName)
    {
        ArrayList<String> list = new ArrayList<String>();
        JSONArray jsonArray = mJsonResponse.optJSONArray(QUIZ_JSON_LIST);
        quizModelArrayList = new ArrayList<QuizModel>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject object = (JSONObject) jsonArray.get(i);
                JSONArray jsonModuleArray = object.optJSONArray(moduleName);
                for (int j = 0; j < jsonModuleArray.length(); j++) {
                    JSONObject objectQuestion = (JSONObject) jsonModuleArray.get(j);
                    QuizModel quizModel = new QuizModel(objectQuestion);

                    quizModelArrayList.add(quizModel);
                    Log.e("Siva", "quizModelArrayList length--->"+quizModelArrayList.size());
                }
            } catch (JSONException e) {
                Log.e(TAG, "Exception While converting JSONArray to ArrayList---"+e.toString());
            }

        }

    }

    public ArrayList<QuizModel> getQuizModelArrayList() {
        return quizModelArrayList;
    }

    public String getFirstScreen()
    {
        return mJsonResponse.optString(JsonMap.FIRST_SCREEN);
    }

    public String getEmail()
    {
        return mJsonResponse.optString(JsonMap.EMAIL);
    }

    public void readFromFile()
    {
        try
        {
            String currentLine, jsonString = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(mContext.getAssets().open("jesus_quiz.json")));

            while((currentLine = br.readLine()) != null)
            {
                jsonString = jsonString.trim()+currentLine.trim();
            }
            this.setJsonResponse(new JSONObject(jsonString));

        }
        catch (IOException e)
        {
            Log.w(TAG, e);
        }
        catch (JSONException e)
        {
            Log.w(TAG, e);
        }

    }

    public void clear(){
        mJsonResponse = null;
        quizModelArrayList = null;
    }

    interface JsonMap
    {
        String SKIN_CARE_ONLINE = "SkinCareOnline";
        String FIRST_SCREEN = "FirstScreen";
        String EMAIL = "Email";
    }
}
