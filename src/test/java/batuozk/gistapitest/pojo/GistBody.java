package batuozk.gistapitest.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class GistBody {
    @SerializedName("description")
    private String description;
    @SerializedName("public")
    private Boolean isPublic;
    @SerializedName("files")
    private HashMap<String, HashMap<String, String>> files;

    public GistBody(String description, Boolean isPublic, String fileName, String contentDesc){
        HashMap<String, String> contentMap =new HashMap<>(){{
            put("content", contentDesc);
        }};
        this.description = description;
        this.isPublic = isPublic;
        this.files = new HashMap<>(){{
            put(fileName, contentMap);
        }};
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }
}
