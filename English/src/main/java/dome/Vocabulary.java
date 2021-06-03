package dome;

public class Vocabulary {
    private int id;
    private String words;
    private String pronunciation;
    private String meaning;
    private int status;

    public Vocabulary(String words, String pronunciation, String meaning, int status) {
        this.words = words;
        this.pronunciation = pronunciation;
        this.meaning = meaning;
        this.status = status;
    }

    public Vocabulary(int id, String words, String pronunciation, String meaning, int status) {
        this.id = id;
        this.words = words;
        this.pronunciation = pronunciation;
        this.meaning = meaning;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWords() {
        return words;
    }

    public void setWords( String words) {
        this.words = words;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public int getStatus(){
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", words=" + words + ", lastName=" + pronunciation + ", meaning=" + meaning + ", status" + status + "]";
    }
}

