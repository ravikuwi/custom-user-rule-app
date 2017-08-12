package custom.user.rule.app.content.targeting.rule.pojo;

import java.util.HashMap;
import java.util.Map;

public class ProfessionPojo {

    long userId;
    String Profession;
    static  Map<Long, ProfessionPojo> mapProfessionPojo = null;

    public String getProfession() {
        return Profession;
    }

    public void setProfession(String profession) {
        Profession = profession;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public ProfessionPojo(long userId, String profession) {
        this.userId = userId;
        Profession = profession;
    }

    public static Map<Long,ProfessionPojo> getUserProfessionPojos(){

       if(mapProfessionPojo==null) {
           mapProfessionPojo = new HashMap<>();
           mapProfessionPojo.put(42207L, new ProfessionPojo(42207, "Teacher"));
           mapProfessionPojo.put(42221L, new ProfessionPojo(42221, "Artist"));
           return mapProfessionPojo;
       }else{
           return mapProfessionPojo;
       }
    }


    @Override
    public String toString() {
        return "ProfessionPojo{" +
                "userId=" + userId +
                ", Profession='" + Profession + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfessionPojo that = (ProfessionPojo) o;

        if (userId != that.userId) return false;
        return Profession.equals(that.Profession);
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + Profession.hashCode();
        return result;
    }
}
