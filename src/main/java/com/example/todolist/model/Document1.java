package com.example.todolist.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="Tasks")
public class Document1 
{
    @Id
    private String Id;
    private String tdate;
    private String tname;
    private String ttime;
    @Indexed(unique = true)
    private int ttasknum;
    private String demail;


    public Document1()
    {

    }
    public Document1(String tdate,String tname,String ttime,int ttasknum,String demail)
    {
      this.tdate=tdate;
      this.tname=tname;
      this.ttasknum=ttasknum;
      this.ttime=ttime;
      this.demail=demail;
    }
    
    public String getId(){return this.Id;}
    public void setId(String Id){this.Id=Id;}
    public void settdate(String Date){this.tdate=Date;}
    public String gettdate(){return this.tdate;}
    public void settname(String Name){this.tname=Name;}
    public String gettname(){return this.tname;}
    public void setttime(String Time){this.ttime=Time;}
    public String getttime(){return this.ttime;}
    public void setttasknum(int TaskNum){this.ttasknum=TaskNum;}
    public int getttaskNum(){return this.ttasknum;}
    public void setdemail(String demail){this.demail=demail;}
    public String getdemail(){return this.demail;}
}
