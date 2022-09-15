package batuozk.gistapitest.pojo;

import com.google.gson.annotations.SerializedName;

public class FileContent {
    @SerializedName("content")
    private String content;

    public FileContent(String content){
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
