package batuozk.gistapitest.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class Files {
    @SerializedName("fileKey")
    private HashMap<String, FileContent> fileKey;

    public Files(String fileKeyStr, String content){
        fileKey = new HashMap<>();
        this.fileKey.put(fileKeyStr, new FileContent(content));
    }

    public HashMap<String, FileContent> getFileKey() {
        return fileKey;
    }

    public void setFileKey(HashMap<String, FileContent> fileKey) {
        this.fileKey = fileKey;
    }
}
