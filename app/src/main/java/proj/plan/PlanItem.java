package proj.plan;

import org.w3c.dom.Text;

public class PlanItem {
    private String mTextTime;
    private String mTextSubject;
    private String mTextType;
    private String mTextTeacher;
    private String mTextRoom;

    public PlanItem(String TextTime, String TextSubject, String TextType, String TextTeacher, String TextRoom)
    {
        mTextTime = TextTime;
        mTextSubject = TextSubject;
        mTextType = TextType;
        mTextTeacher = TextTeacher;
        mTextRoom = TextRoom;
    }

    public String getmTextTime() {
        return mTextTime;
    }

    public String getmTextSubject() {
        return mTextSubject;
    }

    public String getmTextType() {
        return mTextType;
    }

    public String getmTextTeacher() {
        return mTextTeacher;
    }

    public String getmTextRoom() {
        return mTextRoom;
    }
}
