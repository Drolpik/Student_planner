package proj.plan;

public class PlanItem {

    /**
     * Dane przedmiotu
     */
    private String mTextTime;
    private String mTextSubject;
    private String mTextType;
    private String mTextTeacher;
    private String mTextRoom;
    private String mID;
    private String mDay;

    /**
     * Tworzy nową instancję przedmiotu w widoku
     * @param TextTime Czas rozpoczęcia
     * @param TextSubject Nazwa przedmiotu
     * @param TextType Rodzaj zajęć
     * @param TextTeacher Inicjały nauczyciela
     * @param TextRoom Sala
     * @param day Dzień
     * @param id Identyfikator pobrany z serwera (do synchronizaji)
     */
    public PlanItem(String TextTime, String TextSubject, String TextType, String TextTeacher, String TextRoom, String day, String id)
    {
        mTextTime = TextTime;
        mTextSubject = TextSubject;
        mTextType = TextType;
        mTextTeacher = TextTeacher;
        mTextRoom = TextRoom;
        mDay = day;
        mID = id;
    }

    /**
     * Zmiana czasu rozpoczęcia
     * @param time Nowy czas
     */
    public void changeTime(String time) { mTextTime = time; }

    /**
     * Zmiana nazwy przedmiotu
     * @param subject Nowa nazwa
     */
    public void changeSubject(String subject) { mTextSubject = subject; }

    /**
     * Zmiana rodzaju zajęć
     * @param type Nowy rodzaj
     */
    public void changeType(String type) { mTextType = type; }

    /**
     * Zmiana inicjałów nauczyciela
     * @param teacher Nowe inicjały
     */
    public void changeTeacher(String teacher) { mTextTeacher = teacher; }

    /**
     * Zmiana sali
     * @param room Nowa sala
     */
    public void changeRoom(String room) { mTextRoom = room; }

    /**
     * Pobranie czasu rozpoczęcia
     * @return Czas
     */
    public String getmTextTime() {
        return mTextTime;
    }

    /**
     * Pobranie nazwy przedmiotu
     * @return Nazwa
     */
    public String getmTextSubject() {
        return mTextSubject;
    }

    /**
     * Pobranie rodzaju zajęć
     * @return Rodzaj
     */
    public String getmTextType() {
        return mTextType;
    }

    /**
     * Pobranie inicjałów nauczyciela
     * @return Inicjały
     */
    public String getmTextTeacher() {
        return mTextTeacher;
    }

    /**
     * Pobranie sali
     * @return Sala
     */
    public String getmTextRoom() {
        return mTextRoom;
    }

    /**
     * Pobranie dnia
     * @return Dzień
     */
    public String getmDay() {
        return mDay;
    }

    /**
     * Pobranie identyfikatora
     * @return Identyfikator
     */
    public String getmID() { return mID; }
}
