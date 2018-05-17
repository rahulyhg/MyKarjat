
package harshad.mykarjat;

/**
 * Created by family on 27/3/18.
 */

public class fbase {
    private String name,shopname,address,speciality,phone,bstype,lat,lon,email;
    private String update;
    private String date;
    private String chatmsg,chatusername,chattime;
    private String username,password;
    private String keywords;

    public fbase(){
    }

    /*
    public String get(){
        return ;
    }

    public void set(String ){
        this.=;
    }
    */


    public String getChattime(){
        return chattime;
    }

    public void setChattime(String chattime){
        this.chattime=chattime;
    }

    public String getChatusername(){
        return chatusername;
    }

    public void setChatusername(String chatusername){
        this.chatusername=chatusername;
    }

    public String getKeywords(){
        return keywords;
    }

    public void setKeywords(String keywords ){
        this.keywords=keywords;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }
    public String getChatmsg(){
        return chatmsg;
    }

    public void setChatmsg(String chatmsg){
        this.chatmsg=chatmsg;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date=date;
    }


    public String getUpdate(){
        return update;
    }

    public void setUpdate(String update ){
        this.update=update;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getShopname(){
        return shopname;
    }
    public void setShopname(String shopname){
        this.shopname=shopname;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }

    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }

    public String getLat(){
        return lat;
    }
    public void setLat(String lat){
        this.lat=lat;
    }

    public String getLon(){
        return lon;
    }
    public void setLon(String lon){
        this.lon=lon;
    }

    public String getSpeciality(){
        return speciality;
    }
    public void setSpeciality(String speciality){
        this.speciality=speciality;
    }

    public String getBstype(){
        return bstype;
    }
    public void setBstype(String bstype ){
        this.bstype=bstype;
    }
}
