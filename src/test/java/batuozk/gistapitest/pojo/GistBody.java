package batuozk.gistapitest.pojo;

import com.google.gson.annotations.SerializedName;

public class GistBody {
    @SerializedName("description")
    private String description;
    @SerializedName("public")
    private Boolean isPublic;
    @SerializedName("files")
    private Files files;

    public GistBody(String description, Boolean isPublic, String fileKey, String contentDesc){
        this.description = description;
        this.isPublic = isPublic;
        this.files = new Files(fileKey,contentDesc);
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

    public Files getFiles() {
        return files;
    }

    public void setFiles(Files files) {
        this.files = files;
    }
}
