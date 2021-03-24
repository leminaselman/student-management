package com.gtappdevelopers.gfgroomdatabase;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public class StudentModal {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String identity;

    private String email;

    private String gender;

    private String birth;

    private String paying;

    public StudentModal(String name, String identity, String email, String gender, String birth,  String paying) {
        this.name=name;
        this.identity=identity;
        this.email=email;
        this.gender=gender;
        this.birth=birth;
        this.paying=paying;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPaying() {
        return paying;
    }

    public void setPaying(String paying) {
        this.paying = paying;
    }

}
